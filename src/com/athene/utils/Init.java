package com.athene.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.athene.R;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache.CompressListener;
import cn.trinea.android.common.service.impl.ImageMemoryCache.OnImageCallbackListener;

/**
 * Init
 *
 * @author gxwu@lewatek.com 2014-3-4
 */
public class Init {

    public static void init() {
        Cache.ICON_CACHE.setOnImageCallbackListener(new OnImageCallbackListener() {

            private static final long serialVersionUID = 1L;

            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                Cache.onIconLoaded(imageUrl, loadedImage, view, isInCache);
            }

            public void onPreGet(String imageUrl, View view) {

            }

            public void onGetNotInCache(String imageUrl, View view) {
                if (view != null && view instanceof ImageView) {
                    ((ImageView) view).setImageResource(R.drawable.icon_default);
                }
            }

            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {

            }
        });
        // intelligent compress image, to avoid oom
        Cache.IMAGE_CACHE.setCompressListener(new CompressListener() {

            public int getCompressSize(String imagePath) {
                return Cache.getImageScale(imagePath);
            }
        });
        Cache.IMAGE_CACHE.setOnImageCallbackListener(new OnImageCallbackListener() {

            private static final long serialVersionUID = 1L;

            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                Cache.onImageLoaded(imageUrl, loadedImage, view, isInCache);
            }

            public void onGetNotInCache(String imageUrl, View view) {
                if (view != null) {
                    if (view instanceof ImageView) {
                        Boolean noDefaultIcon = null;
                        try {
                            noDefaultIcon = (Boolean) view.getTag(R.id.tag_no_default_icon);
                            if (noDefaultIcon != null && noDefaultIcon) {
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ((ImageView) view).setImageResource(R.drawable.default_icon);
                    } else {
                        view.setBackgroundResource(R.drawable.default_icon);
                    }
                }
            }

            public void onPreGet(String imageUrl, View view) {

            }

            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {

            }
        });
    }
}
