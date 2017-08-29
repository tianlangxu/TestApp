package com.example.administrator.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.testapp.util.TouchUtil;

/**
 * 自定义控件
 */

public class CustomView extends View {
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 绘制界面
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(10, 10, 100, 100);
        Paint paint = new Paint();

//        // 设置画笔抗锯齿
        paint.setAntiAlias(true);
//        // 设置圆形内容不填充
//        paint.setStyle(Paint.Style.STROKE);
//        // 设置边宽为5
//        paint.setStrokeWidth(5);
//        // 设置颜色为红色
        paint.setColor(Color.RED);
//
//        // 绘制一个正方形
        canvas.drawRect(rectF,paint);
//
//        // 绘制一个圆形
//        canvas.drawOval(rectF, paint);
//
////        canvas.drawLine(0,0,100,100,paint);


//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//
//        Path path = new Path();
//
//        path.moveTo(100, 100);
////        path.lineTo(300,100);
//        // 画曲线
//        path.quadTo(200, 200, 300, 100);
//        canvas.drawPath(path, paint);
    }

    /**
     * 设置空间的尺寸大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtil.logTouch(event,"CustomView");
        return super.onTouchEvent(event);
    }
}
