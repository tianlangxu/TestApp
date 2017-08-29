package com.example.administrator.testapp.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/8/22.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };

    public DividerItemDecoration(Context context, int orientation) {
        // 获取到系统默认的图片资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("Please set orientation LinearLayoutManager.HORIZONTAL or LinearLayoutManager.VERTICAL");
        }
        this.mOrientation = orientation;
    }

    public DividerItemDecoration(Drawable divider) {
        this.mDivider = divider;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            // 垂直方向
            drawVertical(c, parent);
        } else {
            // 水平方向
            drawHorizontal(c, parent);
        }

    }


    private void drawVertical(Canvas c, RecyclerView parent) {
        // 绘制水平线
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();


        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 绘制竖直方向
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();


        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (mOrientation == LinearLayoutManager.VERTICAL) {
            // 垂直方向
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            // 水平方向
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);

        }
    }
}
