package com.example.administrator.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.administrator.testapp.util.TouchUtil;

/**
 * Created by Administrator on 2017/7/26.
 */

public class CustomViewGroup extends ViewGroup {
    private int width = 0;
    private int height = 0;

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int childWidthMax = getChildWidthMax();

        int childHeightSum = getChildHeightSum();

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = childWidthMax;
                break;

            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;

        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = childHeightSum;
                break;

            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 获取子空间中最宽的宽度
     *
     * @return
     */
    public int getChildWidthMax() {
        int maxWidth = 0;
        int countChildView = getChildCount();
        for (int i = 0; i < countChildView; i++) {
            int measuredWidth = getChildAt(i).getMeasuredWidth();
            if (maxWidth < measuredWidth) {
                maxWidth = measuredWidth;
            }

        }
        return maxWidth;
    }

    /**
     * 获取空间总高度
     *
     * @return
     */
    public int getChildHeightSum() {
        int heightSum = 0;
        int countChildView = getChildCount();
        for (int i = 0; i < countChildView; i++) {
            int measuredHeight = getChildAt(i).getMeasuredHeight();
            heightSum += measuredHeight;

        }
        return heightSum;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtil.logTouch(event,"CustomViewGroup");
        return super.onTouchEvent(event);
    }
}
