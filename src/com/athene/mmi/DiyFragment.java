package com.athene.mmi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.athene.R;
import com.athene.adapter.HScrollViewAdapter;
import com.athene.adapter.WeddingDIYGridAdapter;
import com.athene.widgets.DIYNaviItem;
import com.athene.widgets.HScrollView;
import com.athene.widgets.NaviItem;
import com.viewpagerindicator.TabPageIndicator;

public class DiyFragment extends Fragment {
    private static final int IDX_WEDD_DIY = 0;
    private static final int IDX_MY_DIY = 1;
    private static final int MAX_NAVI_ITEMS = 2;
    
    private View mVRootView;
    private GridView mWeddingGridView;
    private WeddingDIYGridAdapter mWeddingAdapter;
    private DIYNaviItem[] mNaviItems;
    
    private View.OnClickListener mNaviItemClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ni_wedd:
                    setNaviSelection(IDX_WEDD_DIY);
                    break;
                case R.id.ni_my:
                    setNaviSelection(IDX_MY_DIY);
                    break;
                default:
                    break;
            }
        }
    };
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mVRootView = inflater.inflate(R.layout.fragment_diy, container, false);
        
        initNaviIndicator();
        initWeddingDIYLayout();
        return mVRootView;
    }
    
    private void initNaviIndicator() {
        mNaviItems = new DIYNaviItem[MAX_NAVI_ITEMS];
        mNaviItems[0] = (DIYNaviItem) mVRootView.findViewById(R.id.ni_wedd);
        mNaviItems[1] = (DIYNaviItem) mVRootView.findViewById(R.id.ni_my);

        for (DIYNaviItem item : mNaviItems) {
            item.setOnClickListener(mNaviItemClickListener);
        }

        setNaviSelection(IDX_WEDD_DIY);
    }
    
    private void initWeddingDIYLayout() {
        mWeddingGridView = (GridView) mVRootView.findViewById(R.id.grid_wedding_diy);
        mWeddingAdapter = new WeddingDIYGridAdapter(getActivity());
        mWeddingGridView.setAdapter(mWeddingAdapter);
    }
    
    private void setNaviSelection(int index) {
        clearNaviSelection();
        mNaviItems[index].setSelected(true);
        
        switch (index) {
        case IDX_WEDD_DIY: {
                if (mWeddingGridView != null) {
                    mWeddingGridView.setVisibility(View.VISIBLE);
                }
            }
            break;
        case IDX_MY_DIY: {
                if (mWeddingGridView != null) {
                    mWeddingGridView.setVisibility(View.GONE);
                }
            }
            break;
        default:
            break;
        }
    }
    
    private void clearNaviSelection() {
        for (DIYNaviItem item : mNaviItems) {
            item.setSelected(false);
        }
    }
}
