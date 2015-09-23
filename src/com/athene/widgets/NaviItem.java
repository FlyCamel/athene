package com.athene.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.athene.R;

/**
 * Created by bing on 6/1/15.
 */
public class NaviItem extends LinearLayout {
    private Context mContext;

    private TextView mNaviTitle;
    private ImageView mNaviIcon;

    private boolean mIsSelected;
    private Drawable mSelectedIcon;
    private Drawable mUnselectedIcon;
    private int mSelectedColor;
    private int mUnselectedColor;
    private String mText;

    public NaviItem(Context context) {
        this(context, null);
    }

    public NaviItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NaviItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NaviItem, defStyle, 0);
            mText = a.getString(R.styleable.NaviItem_text);
            mSelectedIcon = a.getDrawable(R.styleable.NaviItem_sel_icon);
            mUnselectedIcon = a.getDrawable(R.styleable.NaviItem_unsel_icon);
            mSelectedColor = a.getColor(R.styleable.NaviItem_sel_color, Color.WHITE);
            mUnselectedColor = a.getColor(R.styleable.NaviItem_unsel_color, Color.GRAY);
            mIsSelected = a.getBoolean(R.styleable.NaviItem_selected, false);
            a.recycle();
        }

        initLayout();
    }

    private void initLayout() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.navi_item, this, true);
        mNaviTitle = (TextView) view.findViewById(R.id.title);
        mNaviTitle.setClickable(false);
        mNaviTitle.setFocusable(false);
        if (mText == null || mText.isEmpty()) {
            mNaviTitle.setVisibility(View.GONE);
        } else {
            mNaviTitle.setText(mText);
        }

        mNaviIcon = (ImageView) view.findViewById(R.id.icon);
        mNaviIcon.setClickable(false);
        mNaviIcon.setFocusable(false);
        mNaviIcon.setBackgroundDrawable(mUnselectedIcon);

        this.setClickable(true);
        this.setFocusable(true);
    }

    /**
     * Set the navigation item as selected state
     */
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        if (selected) {
            mNaviTitle.setTextColor(mSelectedColor);
            if (mSelectedIcon != null) {
                mNaviIcon.setBackgroundDrawable(mSelectedIcon);
            }
        } else {
            mNaviTitle.setTextColor(mUnselectedColor);
            if (mUnselectedIcon != null) {
                mNaviIcon.setBackgroundDrawable(mUnselectedIcon);
            }
        }
    }
}
