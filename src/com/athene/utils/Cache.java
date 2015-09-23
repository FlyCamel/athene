package com.athene.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;

import com.athene.MyApplication;
import com.athene.R;
import com.athene.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageSDCardCache;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;
import cn.trinea.android.common.util.ImageUtils;
import cn.trinea.android.common.util.JSONUtils;
import cn.trinea.android.common.util.ObjectUtils;
import cn.trinea.android.common.util.StringUtils;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * global thread pool and image cache
 *
 * @author gxwu@lewatek.com 2012-11-13
 */
public class Cache {

    public static class MyThreadFactory implements ThreadFactory {

        private String threadPoolName;

        public MyThreadFactory(String threadPoolName) {
            this.threadPoolName = threadPoolName;
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, threadPoolName);
        }
    }

    /**
     * Signle scheduled to access db *
     */
    public static ScheduledExecutorService THREAD_POOL_ACCESS_DB = Executors.newSingleThreadScheduledExecutor(new MyThreadFactory(
            "db thread pool"));

    /**
     * Scheduled update progressBar thread pool *
     */
    private static ScheduledExecutorService SCHEDULED_THREAD_POOL = Executors.newScheduledThreadPool(Runtime.getRuntime()
            .availableProcessors() * 2 + 1);
    private static final long DEFAULT_INITIAL_DELAY = 0;
    private static final long DEFAULT_PERIOD = 3;
    public static final long DEFAULT_SEND_MESSAGE_DELAY = 100;
    public static final String TAG_ICON_CACHE = "image_cache";
    public static final String TAG_IMAGE_CACHE = "image_sdcard_cache";

    private static int IMAGE_MAX_WIDTH = 480;
    private static int IMAGE_MAX_HEIGHT = 960;
    private static boolean HAS_NOT_GOT_SIZE = true;

    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command) {
        return scheduleAtFixedRate(command, DEFAULT_PERIOD);
    }

    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long rateMills) {
        return SCHEDULED_THREAD_POOL.scheduleAtFixedRate(command, DEFAULT_INITIAL_DELAY, rateMills, TimeUnit.SECONDS);
    }

    public static ScheduledFuture<?> schedule(Runnable command, long delayTimeMills) {
        return SCHEDULED_THREAD_POOL.schedule(command, delayTimeMills, TimeUnit.MILLISECONDS);
    }

    /**
     * thread pool *
     */
    public static ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    /**
     * icon cache *
     */
    public static final ImageCache ICON_CACHE = new ImageCache(128, 4, ImageSDCardCache.DEFAULT_MAX_SIZE, 4);
    /**
     * big image cache, like app detail, banner *
     */
    public static final ImageCache IMAGE_CACHE = new ImageCache(32, 2, 128, 2);

    static {
        ICON_CACHE.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst<Bitmap>());
        ICON_CACHE.setHttpReadTimeOut(10000);
        ICON_CACHE.setOpenWaitingQueue(true);
        ICON_CACHE.setValidTime(-1);
        ICON_CACHE.setRequestProperty("Connection", "false");
        ICON_CACHE.setCacheFolder(Constants.ICON_IMAGE_CACHE_FOLDER);

        IMAGE_CACHE.setCacheFolder(Constants.APP_IMAGE_CACHE_FOLDER);
        IMAGE_CACHE.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst<Bitmap>());
        IMAGE_CACHE.setHttpReadTimeOut(30000);
        IMAGE_CACHE.setOpenWaitingQueue(true);
        IMAGE_CACHE.setValidTime(-1);
        IMAGE_CACHE.setRequestProperty("Connection", "false");

        JSONUtils.isPrintException = false;
    }

    public static void onIconLoaded(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
        if (view != null && loadedImage != null) {
            ImageView imageView = (ImageView) view;
            ScaleType scaleType = null;
            try {
                scaleType = (ScaleType) imageView.getTag(R.id.tag_scale_type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String imageUrlTag = (String) imageView.getTag();
            if (ObjectUtils.isEquals(imageUrlTag, imageUrl)) {
                if (scaleType != null) {
                    imageView.setScaleType(scaleType);
                }
                imageView.setImageBitmap(loadedImage);
                // if not in cache, start animation
                if (!isInCache) {
                    imageView.startAnimation(AnimationUtils.getInAlphaAnimation());
                }
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void onImageLoaded(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
        if (view != null && (view.getTag() == null || ObjectUtils.isEquals((String) view.getTag(), imageUrl))
                && loadedImage != null) {

            if (!(view instanceof ImageView)) {
                view.setBackgroundDrawable(ImageUtils.bitmapToDrawable(loadedImage));
                return;
            }

            ImageView imageView = (ImageView) view;

            // load git image
            if (imageView instanceof GifImageView) {
                String imagePath = IMAGE_CACHE.getImagePath(imageUrl);
                if (!StringUtils.isEmpty(imagePath)) {
                    try {
                        GifDrawable gif = new GifDrawable(imagePath);
                        imageView.setImageDrawable(gif);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return;
            }

            Display display = ((WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE))
                    .getDefaultDisplay();

            Boolean alwaysVertical = null;
            Boolean adjuseHeight = null;
            ScaleType scaleType = null;
            try {
                alwaysVertical = (Boolean) imageView.getTag(R.id.tag_always_vertical);
                adjuseHeight = (Boolean) imageView.getTag(R.id.tag_adjuse_height);
                scaleType = (ScaleType) imageView.getTag(R.id.tag_scale_type);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bitmap bm = loadedImage;
            if (alwaysVertical != null && alwaysVertical && loadedImage.getWidth() > loadedImage.getHeight()) {
                Matrix m = new Matrix();
                m.setRotate(90);
                bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
            }
            if (bm != null) {
                LayoutParams imageParams;
                if (adjuseHeight != null && adjuseHeight
                        && (imageParams = (LayoutParams) imageView.getLayoutParams()) != null) {
                    int width = (imageParams.width == LayoutParams.MATCH_PARENT && display != null)
                            ? display.getWidth() : imageParams.width;
                    if (width > 0) {
                        imageParams.height = width * bm.getHeight() / bm.getWidth();
                        imageView.setScaleType(ScaleType.FIT_XY);
                    }
                }
                if (scaleType != null) {
                    imageView.setScaleType(scaleType);
                }
                imageView.setImageBitmap(bm);
            } else {
                IMAGE_CACHE.remove(imageUrl);
            }
        }
    }

    public static int getImageScale(String imagePath) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, option);

        Display display = ((WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();

        if (HAS_NOT_GOT_SIZE && display != null) {
            IMAGE_MAX_HEIGHT = display.getHeight();
            IMAGE_MAX_WIDTH = display.getWidth();
            HAS_NOT_GOT_SIZE = false;
        }

        int scale = 1;
        while (option.outWidth / scale > IMAGE_MAX_WIDTH || option.outHeight / scale > IMAGE_MAX_HEIGHT) {
            scale *= 2;
        }
        return scale;
    }
}
