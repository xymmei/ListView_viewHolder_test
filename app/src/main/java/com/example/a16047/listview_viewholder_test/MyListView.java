package com.example.a16047.listview_viewholder_test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by 16047 on 2018/3/7.
 */

public class MyListView extends ListView{
    private int mMaxOverDistance;

    public MyListView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mMaxOverDistance=100;
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        float density=metrics.density;
        mMaxOverDistance=(int)(density*mMaxOverDistance);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(
                deltaX,
                deltaY,
                scrollX,
                scrollY,
                scrollRangeX, scrollRangeY,
                maxOverScrollX,mMaxOverDistance,
                isTouchEvent);
    }
}
