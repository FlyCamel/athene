package com.athene.mmi;

import java.util.ArrayList;
import java.util.List;

import com.athene.R;
import com.athene.adapter.WeddingShowListAdapter;
import com.athene.data.WeddingShowInfo;
import com.example.android.bitmapfun.util.Helper;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.viewpagerindicator.CirclePageIndicator;

import android.R.anim;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


public class ShowFragment extends Fragment {
    private static final int BANNER_SIZE = 3;
    private static final String SHOW_URL = "https://m-bingl.rhcloud.com/shows/shows_info" + "?client=1&clientid=860670025167478&user=bing&count=3";
    
    private View mVRootView;
    private View mBannerView;
    private PullToRefreshListView mShowListView;
    private WeddingShowListAdapter mShowListAdapter;
    private ViewPager mViewPager;
    private CirclePageIndicator mPageIndicator;
    private List<View> mPageViews = new ArrayList<View>();
    private List<Drawable> mBannerList = new ArrayList<Drawable>();
    private LoadShowDataTask mLoadDataTask;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mLoadDataTask = new LoadShowDataTask();
        mLoadDataTask.execute(SHOW_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mVRootView = inflater.inflate(R.layout.fragment_show, container, false);
    	
        initPullToRefreshListView();
    	initBannerView(inflater);
        initShowLayout();
        
        return mVRootView;
    }
    
    private void initPullToRefreshListView() {
        mShowListView = (PullToRefreshListView) mVRootView.findViewById(android.R.id.list);
        mShowListView.setMode(Mode.PULL_FROM_END);
        mShowListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                
            }
        });
    }
    
    public void initBannerView(LayoutInflater inflater) {
        mBannerView = inflater.inflate(R.layout.wedding_show_banner_view, null, false);
        ListView listView = mShowListView.getRefreshableView();
        listView.addHeaderView(mBannerView);
        
        mViewPager = (ViewPager) mBannerView.findViewById(R.id.view_pager);
        mPageIndicator = (CirclePageIndicator) mBannerView.findViewById(R.id.indicator);
        
        //for test
        for (int i = 0; i < BANNER_SIZE; i++) {
            mBannerList.add(getActivity().getResources().getDrawable(R.drawable.banner_show));
        }
        
        for (int i = 0; i < BANNER_SIZE; i++) {
            LinearLayout layout = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            if (i != 0) {
//                layoutParams.leftMargin = 10;
            }

            final ImageView imageView = new ImageView(this.getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageDrawable(mBannerList.get(i));
//            imageView.setPadding(15, 30, 15, 30);
            layout.addView(imageView, layoutParams);

            mPageViews.add(layout);
        }

        mViewPager.setAdapter(new BannerPageAdapter());
        mViewPager.setOnPageChangeListener(new BannerPageChangeListener());
        mPageIndicator.setViewPager(mViewPager);
        mPageIndicator.setCurrentItem(0);
    }
    
    public void initShowLayout() {
    	mShowListAdapter = new WeddingShowListAdapter(getActivity());
    	mShowListView.setAdapter(mShowListAdapter);
    }
    
    class BannerPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mPageViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(mPageViews.get(position));
            return mPageViews.get(position);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        public void onPageSelected(int arg0) {
            mPageIndicator.setCurrentItem(arg0);
        }
    }

    public void invalidateDataChanged(WeddingShowInfo info) {
        if (info != null) {
            if (mShowListAdapter != null) {
                mShowListAdapter.setData(info.getShowInfos());
                mShowListAdapter.notifyDataSetChanged();
            }
        }
    }
    
    private class LoadShowDataTask extends AsyncTask<String, Integer, WeddingShowInfo> {

        @Override
        protected WeddingShowInfo doInBackground(String... params) {
            String url = params[0];
            String json = null;
            WeddingShowInfo showInfo = null;
            try {
                json = Helper.getStringFromUrl(url);
                Gson gson = new Gson();
                showInfo = gson.fromJson(json, WeddingShowInfo.class);
            } catch (Exception e) {
                Log.e("jxli", "e = " + e.toString());
            }
            return showInfo;
        }

        @Override
        protected void onPostExecute(WeddingShowInfo result) {
            invalidateDataChanged(result);
        }
    }
}
