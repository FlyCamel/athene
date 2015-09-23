package com.athene.adapter;

import java.util.ArrayList;

import com.athene.R;
import com.athene.data.MainHomeInfo.RecommendSet;
import com.example.android.bitmapfun.util.ImageFetcher;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainWeddPkgListAdapter extends BaseAdapter{
    private static final int SHOW_ITEM_VIEW_COUNTS = 3;
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageFetcher mImageFetcher;
    private ArrayList<RecommendSet> mPackageInfos = new ArrayList<RecommendSet>();

    class PackageInfo {
        String label;
        String price;
        Bitmap bitmap;
        boolean isBoutique;
    }

    class ViewHolder {
        ImageView photoView;
        TextView labelView;
        TextView boutiqueView;
        TextView priceView;
    }
    
    public MainWeddPkgListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImageFetcher = new ImageFetcher(context, 240);
    }
    
    public int getCount() {
        // TODO Auto-generated method stub
        return mPackageInfos.size() > 0 ? SHOW_ITEM_VIEW_COUNTS : 0;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mPackageInfos.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.wedding_packages_item_view, parent, false);
            holder = new ViewHolder();
            holder.photoView = (ImageView) view.findViewById(R.id.photo);
            holder.labelView = (TextView) view.findViewById(R.id.label);
            holder.boutiqueView = (TextView) view.findViewById(R.id.boutique);
            holder.priceView = (TextView) view.findViewById(R.id.price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        RecommendSet info = mPackageInfos.get(position);
        holder.labelView.setText(R.string.hello_world);
        holder.priceView.setText(String.valueOf(info.getPrice()));
        holder.boutiqueView.setVisibility(true ? View.VISIBLE : View.INVISIBLE);
        mImageFetcher.loadImage(info.getImages().get(0), holder.photoView);
        
        return view;
    }

    public void setData(ArrayList<RecommendSet> infos) {
        mPackageInfos = infos;
    }
}
