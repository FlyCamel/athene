package com.athene.mmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.athene.IntentConstants;
import com.athene.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.util.ListUtils;

public class FullscreenViewActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private CirclePageIndicator mPageIndicator;

    /** image url list which we can fling to show **/
    private ArrayList<String> imageUrlList;
    private String currentImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set shows as full screen mode. Must used before setContentView
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.fullscreen_view_pager_indicator);

        initLayout();

        Intent intent = getIntent();
        if (intent != null) {
            initData(intent);
        }
    }

    private void initLayout() {
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mPageIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
    }

    private void initData(Intent intent) {
        currentImageUrl = intent.getStringExtra(IntentConstants.EXTRA_IMAGE_URL);
        imageUrlList = intent.getStringArrayListExtra(IntentConstants.EXTRA_IMAGE_URLS);
        if (!cn.trinea.android.common.util.ListUtils.isEmpty(imageUrlList)) {
            List<Fragment> fragmentList = new ArrayList<Fragment>();
            for (String imageUrl : imageUrlList) {
                FullscreenViewFragment imageViewActivity = new FullscreenViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString(IntentConstants.EXTRA_IMAGE_URL, imageUrl);
                bundle.putString(IntentConstants.EXTRA_SOURCE, IntentConstants.SOURCE_IMAGE_PAGER);
                bundle.putBoolean(IntentConstants.EXTRA_FITXY, false);
                imageViewActivity.setArguments(bundle);
                fragmentList.add(imageViewActivity);
            }
            mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList));
            mPageIndicator.setViewPager(mViewPager);
            /** cache all and view current image first **/
            int index = imageUrlList.indexOf(currentImageUrl);
            if (index < imageUrlList.size() && index >= 0) {
                mPageIndicator.setCurrentItem(index);
            }
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String>   headerList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList){
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

