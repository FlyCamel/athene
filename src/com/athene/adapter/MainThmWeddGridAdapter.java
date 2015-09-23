package com.athene.adapter;

import java.util.ArrayList;
import java.util.List;

import com.athene.R;
import com.athene.data.MainHomeInfo;
import com.athene.data.MainHomeInfo.RecommendTheme;
import com.example.android.bitmapfun.util.ImageFetcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainThmWeddGridAdapter extends BaseAdapter {
    private static final int SHOW_ITEM_VIEW_COUNTS = 4;
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageFetcher mImageFetcher;
    private ArrayList<RecommendTheme> mThemeInfos = new ArrayList<RecommendTheme>();

    class ViewHolder {
        ImageView photoView;
        TextView labelView;
    }

    public MainThmWeddGridAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImageFetcher = new ImageFetcher(context, 240);
    }

    public int getCount() {
        return mThemeInfos.size() > 0 ? SHOW_ITEM_VIEW_COUNTS : 0;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mThemeInfos.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.theme_wedding_item_view, parent, false);
            holder = new ViewHolder();
            holder.photoView = (ImageView) view.findViewById(R.id.photo);
            holder.labelView = (TextView) view.findViewById(R.id.label);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        RecommendTheme info = mThemeInfos.get(position);
        holder.labelView.setText(info.getThemeName());
        mImageFetcher.loadImage(info.getImages().get(0), holder.photoView);
        return view;
    }

    public void setData(ArrayList<RecommendTheme> infos) {
        mThemeInfos = infos;
    }
}
