package com.athene.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.athene.utils.ScreenUtils;

/**
 * Created by bing on 6/3/15.
 */
public class HScrollView extends HorizontalScrollView {
    private int mScreenWitdh;

    /**
     * The container layout of HScrollView
     */
    private LinearLayout mllContainer;
    private Adapter mAdapter;
    private OnItemClickListener mOnItemClickListener;

    private Context mContext;

    private int mHSpacing;

    public HScrollView(Context context) {
        this(context, null);
    }

    public HScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mContext = context;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWitdh = outMetrics.widthPixels;

        this.setHorizontalScrollBarEnabled(false);
    }

    public void setContainerView(LinearLayout layout) {
        mllContainer = layout;
    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;

        resetLayout();
    }


    /**
     * set the horizontal spacing of two image
     *
     * @param space unit is px
     */
    public void setHorizontalSpacing(int space) {
        mHSpacing = space;
    }

    public interface OnItemClickListener {
        void onClick(View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private void resetLayout() {
        if (mllContainer == null) {
            return;
        }

        mllContainer.removeAllViews();

        if (mAdapter != null && mAdapter.getCount() > 0) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                View view = mAdapter.getView(i, null, null);

//                android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
//                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
//                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT);
                android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
                        ScreenUtils.dip2px(mContext, 150),
                        ScreenUtils.dip2px(mContext, 150));
                if (i != 0) {
                    params.leftMargin = mHSpacing;
                }
                view.setLayoutParams(params);
                final int finalI = i;
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onClick(view, finalI);
                        }
                    }
                });
                mllContainer.addView(view);
            }
        }
    }
}
