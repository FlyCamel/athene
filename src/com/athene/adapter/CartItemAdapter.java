package com.athene.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.athene.R;

/**
 * Created by bing on 6/19/15.
 */
public class CartItemAdapter extends BaseAdapter {
    private Context mContext;

    private List<CartItem> mCartList = new ArrayList<CartItem>();

    public CartItemAdapter(Context context) {
        mContext = context;

        demo_initCartData();
    }

    public int getCount() {
        return mCartList.size();
    }

    public Object getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }

        return mCartList.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        if (pos < 0 || pos >= getCount()) {
            return null;
        }

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.pos = pos;
            viewHolder.mCbSelected = (CheckBox) convertView.findViewById(R.id.cb_selected);
            viewHolder.mIvProduct = (ImageView) convertView.findViewById(R.id.iv_product);
            viewHolder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.mTvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);

            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ViewHolder viewHolder = (ViewHolder) view.getTag();
                    if (viewHolder != null) {

                        boolean selected = viewHolder.mCbSelected.isSelected();
                        viewHolder.mCbSelected.setSelected(!selected);
                    }
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CartItem item = mCartList.get(pos);
        if (item != null) {
            viewHolder.mCbSelected.setSelected(item.mSelected);
            viewHolder.mIvProduct.setImageDrawable(item.mImage);
            viewHolder.mTvTitle.setText(item.mTitle);
            viewHolder.mTvPrice.setText("$ " + String.valueOf(item.mPrice));
        }

        return convertView;
    }

    private void demo_initCartData() {
        CartItem item = null;
        for (int i = 0; i < 10; i++) {
            item = new CartItem();

            item.mSelected = false;
            item.mImage = mContext.getResources().getDrawable(R.drawable.list_item_topic_default);
            item.mTitle = "Unified Modeling Language [" + i + "]";
            item.mPrice = 10000 + (100 * i);

            mCartList.add(item);
        }
    }

    class ViewHolder {
        int pos;
        CheckBox mCbSelected;
        ImageView mIvProduct;
        TextView mTvTitle;
        TextView mTvPrice;
    }

    class CartItem {
        boolean mSelected;
        Drawable mImage;
        String mTitle;
        float mPrice;

        public CartItem() {
        }

        public CartItem(boolean selected, Drawable image, String title, float price) {
            mSelected = selected;
            mImage = image;
            mTitle = title;
            mPrice = price;
        }
    }
}
