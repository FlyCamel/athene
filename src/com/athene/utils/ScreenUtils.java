package com.athene.utils;

import android.content.Context;

/**
 * Add by huzeyin 2014.6.17
 */
public class ScreenUtils {

    /**
     * Dp To Px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Px To Dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
