package com.athene.adapter;

import java.util.ArrayList;

import com.athene.R;
import com.athene.data.WeddingShowInfo.ShowInfo;
import com.example.android.bitmapfun.util.ImageFetcher;

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

public class WeddingShowListAdapter extends BaseAdapter{
	private Context mContext;
    private LayoutInflater mInflater;
    private ImageFetcher mImageFetcher;
    private ArrayList<ShowInfo> mShowInfos = new ArrayList<ShowInfo>();

    class ViewHolder {
        TextView nameView;
        TextView messageView;
        TextView watchView;
        TextView commentView;
        ImageView avatarView;
        ImageView photoView1;
        ImageView photoView2;
        ImageView photoView3;
    }
    
    public WeddingShowListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mImageFetcher = new ImageFetcher(context, 240);
    }
    
    public int getCount() {
        // TODO Auto-generated method stub
        return mShowInfos != null ? mShowInfos.size() : 0;
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
        Log.e("jxli", "pos = " + position);
        ViewHolder holder;
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.wedding_show_list_item_view, parent, false);
            holder = new ViewHolder();
            holder.avatarView = (ImageView) view.findViewById(R.id.avatar);
            holder.photoView1 = (ImageView) view.findViewById(R.id.photo1);
            holder.photoView2 = (ImageView) view.findViewById(R.id.photo2);
            holder.photoView3 = (ImageView) view.findViewById(R.id.photo3);
            holder.nameView = (TextView) view.findViewById(R.id.name);
            holder.messageView = (TextView) view.findViewById(R.id.message);
            holder.watchView = (TextView) view.findViewById(R.id.watch_count);
            holder.commentView = (TextView) view.findViewById(R.id.comment_count);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ShowInfo info = mShowInfos.get(position);
        holder.nameView.setText(info.getUserName());
        holder.messageView.setText(info.getTitle());
        holder.watchView.setText(String.valueOf(info.getViewCount()));
        holder.commentView.setText(String.valueOf(info.getCommentCount()));
        mImageFetcher.loadImage(info.getUserIconUrl(), holder.avatarView);
        mImageFetcher.loadImage(info.getImagesUrl().get(0), holder.photoView1);
        mImageFetcher.loadImage(info.getImagesUrl().get(1), holder.photoView2);
        mImageFetcher.loadImage(info.getImagesUrl().get(2), holder.photoView3);
        return view;
    }

    public void setData(ArrayList<ShowInfo> infos) {
        mShowInfos = infos;
    }
}
