package com.athene.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class ExpandListView extends ListView{

    public ExpandListView(Context context) {
        this(context, null);
    }

    public ExpandListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Expand the height value.
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
