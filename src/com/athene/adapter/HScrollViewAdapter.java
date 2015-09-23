package com.athene.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.athene.R;
import com.athene.utils.Cache;
import com.athene.utils.DiskUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing on 6/3/15.
 */
public class HScrollViewAdapter extends BaseAdapter {
    private Context mContext;

    //private List<String> mImages;
    private List<Drawable> mImages;

    public HScrollViewAdapter(Context context) {
        mContext = context;

        mImages = new ArrayList<Drawable>();
        demo_initImagesData();
    }

    public int getCount() {
        return mImages == null ? 0 : mImages.size();
    }

    public Object getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }

        return mImages.get(i);
    }

    public long getItemId(int pos) {
        return pos;
    }

//    public void setAdapterData(List<String> urlList) {
//        mImages = urlList;
//    }


    private void demo_initImagesData() {
        String hsvDataDir = "/sdcard/athene/hsview_demo/";
        List<String> images = DiskUtils.getPathFiles(hsvDataDir);

        for (String image : images) {
            mImages.add(Drawable.createFromPath(image));
        }
    }

    public List<String> demo_getImagesData() {
        String hsvDataDir = "/sdcard/athene/hsview_demo/";
        return DiskUtils.getPathFiles(hsvDataDir);
    }

//    public List<String> getAdapterData() {
//        return mImages;
//    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        if (pos < 0 || pos >= getCount()) {
            return null;
        }

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.himage_view_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mIvImage = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.mTvText = (TextView) convertView.findViewById(R.id.text);
            viewHolder.pos = pos;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

//        String url = mImageUrlList.get(pos);
//        if (url != null) {
//            viewHolder.mIvImage.setTag(R.id.tag_no_default_icon, true);
//            viewHolder.mIvImage.setTag(url);
//            viewHolder.mIvImage.setTag(R.id.tag_scale_type, ImageView.ScaleType.FIT_XY);
//            viewHolder.mIvImage.setTag(R.id.tag_always_vertical, true);
//            Cache.IMAGE_CACHE.get(url, viewHolder.mIvImage);
//        }

        // TODO: Set data from $mImages
        viewHolder.mIvImage.setBackgroundDrawable(mImages.get(pos));

        return convertView;
    }

    class ViewHolder {
        int pos;
        ImageView mIvImage;
        TextView mTvText;
    }
}
