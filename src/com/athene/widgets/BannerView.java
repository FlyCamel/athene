package com.athene.widgets;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.athene.MyApplication;
import com.athene.utils.DiskUtils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import cn.trinea.android.common.util.ListUtils;

/**
 * Created by bing on 6/5/15.
 */
public class BannerView extends LinearLayout {

    private Context mContext;

    private ViewPager mViewPager;
    private CirclePageIndicator mIndicator;
    private List<View> mPageViews;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mContext = context;
    }

    public void setParentActivity(Activity activity) {

    }

    public void setData(List<String> files, int pos) {

    }

    public List<String> getData() {
        return null;
    }


    private class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String>   headerList;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList){
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ListUtils.getSize(headerList) <= position ? null : headerList.get(position);
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        /**
         * get headerList
         *
         * @return the headerList
         */
        public List<String> getHeaderList() {
            return headerList;
        }

        /**
         * set headerList
         *
         * @param headerList the headerList to set
         */
        public void setHeaderList(List<String> headerList) {
            this.headerList = headerList;
        }
    }
}

