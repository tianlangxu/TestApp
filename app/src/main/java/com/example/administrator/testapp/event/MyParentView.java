package com.example.administrator.testapp.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.administrator.testapp.util.TouchUtil;

/**
 * 父控件
 */

public class MyParentView extends FrameLayout {

    public MyParentView(Context context) {
        this(context, null);
    }

    public MyParentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtil.logTouch(event,"MyParentView");
        return super.onTouchEvent(event);
    }

}
