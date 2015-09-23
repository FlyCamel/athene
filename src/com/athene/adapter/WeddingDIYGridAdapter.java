package com.athene.adapter;

import java.util.ArrayList;

import com.athene.R;
import com.athene.adapter.MainTabGridAdapter.ItemInfo;
import com.athene.adapter.MainTabGridAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeddingDIYGridAdapter extends BaseAdapter{
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

    public WeddingDIYGridAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

        marrInfos.add(new ItemInfo(R.string.label_groom, R.drawable.ic_groom));
        marrInfos.add(new ItemInfo(R.string.label_bride, R.drawable.ic_bride));
        marrInfos.add(new ItemInfo(R.string.label_sign_desk, R.drawable.ic_sign_desk));
        marrInfos.add(new ItemInfo(R.string.label_welcome_bg, R.drawable.ic_welcome_bg));
        marrInfos.add(new ItemInfo(R.string.label_main_stage, R.drawable.ic_main_stage));
        marrInfos.add(new ItemInfo(R.string.label_wedding_car, R.drawable.ic_wedd_car));
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
            view = mInflater.inflate(R.layout.wedding_diy_grid_item_view, parent, false);
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
