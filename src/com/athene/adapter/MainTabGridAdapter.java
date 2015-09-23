package com.athene.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import org.w3c.dom.Text;

import com.athene.R;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainTabGridAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    private ArrayList<ItemInfo> marrInfos = new ArrayList<ItemInfo>();

    class ItemInfo {
        int titleId;
        int iconId;

        public ItemInfo(int titleId, int iconId) {
            this.titleId = titleId;
            this.iconId = iconId;
        }
    }

    class ViewHolder {
        ImageView iconView;
        TextView titleView;
    }

    public MainTabGridAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

        marrInfos.add(new ItemInfo(R.string.title_theme_wedding, R.drawable.ic_theme_wedding));
        marrInfos.add(new ItemInfo(R.string.title_wedding_packages, R.drawable.ic_wedding_package));
        marrInfos.add(new ItemInfo(R.string.title_wedding_diy, R.drawable.ic_wedding_diy));
        marrInfos.add(new ItemInfo(R.string.title_wedding_photo, R.drawable.ic_wedding_photo));
        marrInfos.add(new ItemInfo(R.string.title_wedding_video, R.drawable.ic_wedding_video));
        marrInfos.add(new ItemInfo(R.string.title_lucky_day, R.drawable.ic_lucky_day));
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return marrInfos.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return marrInfos.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.main_tab_item_view, parent, false);
            holder = new ViewHolder();
            holder.iconView = (ImageView) view.findViewById(R.id.icon);
            holder.titleView = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.iconView.setImageResource(marrInfos.get(position).iconId);
        holder.titleView.setText(marrInfos.get(position).titleId);
        return view;
    }
}
