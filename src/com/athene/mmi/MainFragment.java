package com.athene.mmi;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.athene.IntentConstants;
import com.athene.R;
import com.athene.adapter.HScrollViewAdapter;
import com.athene.adapter.MainTabGridAdapter;
import com.athene.adapter.MainThmWeddGridAdapter;
import com.athene.adapter.MainWeddPkgListAdapter;
import com.athene.adapter.MainWeddShowListAdapter;
import com.athene.adapter.RecommendViewAdapter;
import com.athene.data.MainHomeInfo;
import com.athene.data.MainHomeInfo.Banner;
import com.athene.utils.DiskUtils;
import com.athene.widgets.HScrollView;
import com.example.android.bitmapfun.util.Helper;
import com.example.android.bitmapfun.util.ImageFetcher;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String HOME_URL = "https://m-bingl.rhcloud.com/home/home_info" + "?client=1&clientid=860670025167478&user=bing";
    
    private View mVRootView;
    private View mBannerView;
    private ImageView mBannerBackgroundView;
    private GridView mGridTab;
    private MainTabGridAdapter mTabGridAdapter;
    private GridView mGridThemeWedding;
    private MainThmWeddGridAdapter mThmWeddGridAdapter;
    private ListView mLvWeddingPackages;
    private MainWeddPkgListAdapter mWeddPkgListAdapter;
    private ListView mLvWeddingShow;
    private MainWeddShowListAdapter mWeddShowListAdapter;
    private LoadHomeDataTask mLoadDataTask;
    private ImageFetcher mImageFetcher;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mImageFetcher = new ImageFetcher(getActivity(), 240);
        
        mLoadDataTask = new LoadHomeDataTask();
        mLoadDataTask.execute(HOME_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mVRootView = inflater.inflate(R.layout.fragment_main, container, false);
        initBannerView();        
        initThemeWeddingLayout();
        initWeddingPackagesLayout();
        initWeddingShowLayout();
        return mVRootView;
    }

    private List<String> demo_getImagesData() {
        String hsvDataDir = "/sdcard/athene/hsview_demo/";
        return DiskUtils.getPathFiles(hsvDataDir);
    }

    private void initBannerView() {
        mBannerView = mVRootView.findViewById(R.id.banner);
        mBannerBackgroundView = (ImageView) mVRootView.findViewById(R.id.bg_banner);

        mGridTab = (GridView) mVRootView.findViewById(R.id.tab);
        mTabGridAdapter = new MainTabGridAdapter(getActivity());
        mGridTab.setAdapter(mTabGridAdapter);
    }

    private void initThemeWeddingLayout() {
        mGridThemeWedding = (GridView) mVRootView.findViewById(R.id.grid_theme_wedding);
        mThmWeddGridAdapter = new MainThmWeddGridAdapter(getActivity());
        mGridThemeWedding.setAdapter(mThmWeddGridAdapter);
    }
    
    private void initWeddingPackagesLayout() {
        mLvWeddingPackages = (ListView) mVRootView.findViewById(R.id.list_wedding_packages);
        mWeddPkgListAdapter = new MainWeddPkgListAdapter(getActivity());
        mLvWeddingPackages.setAdapter(mWeddPkgListAdapter);
    }
    
    private void initWeddingShowLayout() {
        mLvWeddingShow = (ListView) mVRootView.findViewById(R.id.list_wedding_show);
        mWeddShowListAdapter = new MainWeddShowListAdapter(getActivity());
        mLvWeddingShow.setAdapter(mWeddShowListAdapter);
    }
    
    private void invalidateDataChanged(MainHomeInfo info) {
        if (info != null) {            
            if (info.getBanners() != null) {
                Banner banner = info.getBanners().get(0);
                mImageFetcher.loadImage(banner.getImages().get(0), mBannerBackgroundView);
            }
            
            mThmWeddGridAdapter.setData(info.getRecommendThemes());
            mThmWeddGridAdapter.notifyDataSetChanged();
            
            mWeddPkgListAdapter.setData(info.getRecommendSets());
            mWeddPkgListAdapter.notifyDataSetChanged();
            
            mWeddShowListAdapter.setData(info.getRecommendShows());
            mWeddShowListAdapter.notifyDataSetChanged();
        }
    }
    
    private class LoadHomeDataTask extends AsyncTask<String, Integer, MainHomeInfo> {

        @Override
        protected MainHomeInfo doInBackground(String... params) {
            String url = params[0];
            String json = null;
            MainHomeInfo homeInfo = null;
            try {
                json = Helper.getStringFromUrl(url);
                Gson gson = new Gson();
                homeInfo = gson.fromJson(json, MainHomeInfo.class);
            } catch (Exception e) {
                Log.e("jxli", "e = " + e.toString());
            }
            return homeInfo;
        }

        @Override
        protected void onPostExecute(MainHomeInfo result) {
            invalidateDataChanged(result);
        }
    }
}
