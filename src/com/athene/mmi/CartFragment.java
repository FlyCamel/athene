package com.athene.mmi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.athene.R;
import com.athene.adapter.CartItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CartFragment extends Fragment {
    private View mVRootView;

    private ListView mLvCartList;
    private CartItemAdapter mCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mVRootView = inflater.inflate(R.layout.fragment_cart, container, false);

        initLayout();

        return mVRootView;
    }

    private void initLayout() {
        mCartAdapter = new CartItemAdapter(this.getActivity());

        mLvCartList = (ListView) mVRootView.findViewById(R.id.lv_cart_list);
        mLvCartList.setAdapter(mCartAdapter);

//        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), getData(), R.layout.cart_list_item,
//                new String[] {"selected", "product", "title", "price"},
//                new int [] {R.id.cb_selected, R.id.iv_product, R.id.tv_title, R.id.tv_price});
//        mLvCartList.setAdapter(adapter);

        //setListViewHeightBasedOnChildren(mLvCartList);
    }

    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String,Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("selected", false);
            tempHashMap.put("product", R.drawable.list_item_topic_default);
            tempHashMap.put("title", "Unified Modeling Language [" + i + "]");
            tempHashMap.put("price", "$ " + 10000 + (100 * i));

            arrayList.add(tempHashMap);
        }

        return arrayList;
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
