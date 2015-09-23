package com.athene.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Customize GridView to expand it, avoid to show it's ScrollBar within ScrollBar layout
 */
public class ExpandGridView extends GridView {
    public ExpandGridView(Context context) {
        this(context, null);
    }

    public ExpandGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Expand the height value.
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
