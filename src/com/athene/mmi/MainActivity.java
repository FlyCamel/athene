package com.athene.mmi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.athene.widgets.NaviItem;

import com.athene.R;

public class MainActivity extends Activity {
    private static final int IDX_MAIN = 0;
    private static final int IDX_DIY = 1;
    private static final int IDX_SHOW = 2;
    private static final int IDX_ACCOUNT = 3;
    private static final int MAX_NAVI_ITEMS = 4;

    private FragmentManager mFragmentMgr;

    private MainFragment mMainFragment;
    private ShowFragment mWeddingShowFragment;
    private DiyFragment mWeddingDiyFragment;
    private AccountFragment mAccountFragment;

    private NaviItem[] mNaviItems;

    private View.OnClickListener mNaviItemClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ni_main:
                    setNaviSelection(IDX_MAIN);
                    break;
                case R.id.ni_show:
                    setNaviSelection(IDX_SHOW);
                    break;
                case R.id.ni_diy:
                    setNaviSelection(IDX_DIY);
                    break;
                case R.id.ni_account:
                    setNaviSelection(IDX_ACCOUNT);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        mFragmentMgr = getFragmentManager();

        mNaviItems = new NaviItem[MAX_NAVI_ITEMS];
        mNaviItems[0] = (NaviItem) this.findViewById(R.id.ni_main);
        mNaviItems[1] = (NaviItem) this.findViewById(R.id.ni_diy);
        mNaviItems[2] = (NaviItem) this.findViewById(R.id.ni_show);
        mNaviItems[3] = (NaviItem) this.findViewById(R.id.ni_account);

        for (NaviItem item : mNaviItems) {
            item.setOnClickListener(mNaviItemClickListener);
        }

        setNaviSelection(IDX_MAIN);
    }

    private void setNaviSelection(int index) {
        clearNaviSelection();

        FragmentTransaction transaction = mFragmentMgr.beginTransaction();
        // Hide all fragments first to avoid more than one shows on screen
        hideFragments(transaction);

        switch(index) {
            case IDX_MAIN:
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                    transaction.add(R.id.content, mMainFragment);
                } else {
                    transaction.show(mMainFragment);
                }
                break;
            case IDX_SHOW:
                if (mWeddingShowFragment == null) {
                    mWeddingShowFragment = new ShowFragment();
                    transaction.add(R.id.content, mWeddingShowFragment);
                } else {
                    transaction.show(mWeddingShowFragment);
                }
                break;
            case IDX_DIY:
                if (mWeddingDiyFragment == null) {
                    mWeddingDiyFragment = new DiyFragment();
                    transaction.add(R.id.content, mWeddingDiyFragment);
                } else {
                    transaction.show(mWeddingDiyFragment);
                }
                break;
            case IDX_ACCOUNT:
                if (mAccountFragment == null) {
                    mAccountFragment = new AccountFragment();
                    transaction.add(R.id.content, mAccountFragment);
                } else {
                    transaction.show(mAccountFragment);
                }
                break;
            default:
                return;
        }
        transaction.commit();

        mNaviItems[index].setSelected(true);
    }

    private void clearNaviSelection() {
        for (NaviItem item : mNaviItems) {
            item.setSelected(false);
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }

        if (mWeddingShowFragment != null) {
            transaction.hide(mWeddingShowFragment);
        }

        if (mWeddingDiyFragment != null) {
            transaction.hide(mWeddingDiyFragment);
        }

        if (mAccountFragment != null) {
            transaction.hide(mAccountFragment);
        }
    }
}
