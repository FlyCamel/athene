package com.athene.adapter;

import java.util.ArrayList;

import com.athene.R;
import com.athene.data.MainHomeInfo.RecommendShow;
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

public class MainWeddShowListAdapter extends BaseAdapter{
    private static final int SHOW_ITEM_VIEW_COUNTS = 3;
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageFetcher mImageFetcher;
    private ArrayList<RecommendShow> mShowInfos = new ArrayList<RecommendShow>();

    class ShowInfo {
        String label;
        Bitmap bitmap1;
        Bitmap bitmap2;
        Bitmap bitmap3;
        int praiseCount;
        int evaluationCount;
    }

    class ViewHolder {
        ImageView photoView1;
        ImageView photoView2;
        ImageView photoView3;
        TextView labelView;
        TextView praiseView;
        TextView evaluationView;
    }
    
    public MainWeddShowListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImageFetcher = new ImageFetcher(context, 240);
    }
    
    public int getCount() {
        // TODO Auto-generated method stub
        return mShowInfos.size() > 0 ? SHOW_ITEM_VIEW_COUNTS : 0;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mShowInfos.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.wedding_show_item_view, parent, false);
            holder = new ViewHolder();
            holder.photoView1 = (ImageView) view.findViewById(R.id.photo1);
            holder.photoView2 = (ImageView) view.findViewById(R.id.photo2);
            holder.photoView3 = (ImageView) view.findViewById(R.id.photo3);
            holder.labelView = (TextView) view.findViewById(R.id.label);
            holder.praiseView = (TextView) view.findViewById(R.id.praise_count);
            holder.evaluationView = (TextView) view.findViewById(R.id.evaluation_count);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        RecommendShow info = mShowInfos.get(position);
        holder.labelView.setText(info.getTitle());
        holder.praiseView.setText(String.valueOf(info.getCommentsCount()));
        holder.evaluationView.setText(String.valueOf(info.getProsCount()));
        mImageFetcher.loadImage(info.getImages().get(0), holder.photoView1);
        mImageFetcher.loadImage(info.getImages().get(1), holder.photoView2);
        mImageFetcher.loadImage(info.getImages().get(2), holder.photoView3);
        return view;
    }

    public void setData(ArrayList<RecommendShow> infos) {
        mShowInfos = infos;
    }
}
