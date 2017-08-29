package com.example.administrator.testapp.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import com.example.administrator.testapp.R;

/**
 * Created by Administrator on 2017/8/24.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener moveListener;

    public MyItemTouchHelperCallback(ItemTouchMoveListener moveListener) {
        this.moveListener = moveListener;
    }

    // 用来判断当前的动作
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 监听上下拖拽方向
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 监听左右侧滑方向   如果不想实现设置为0即可
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

//    @Override
//    public boolean isLongPressDragEnabled() {
//        return true;
//    }

    // 拖拽时调用
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder target) {
        // 拖拽过程中会调用adapter中的notifyItemMoved的方法
        boolean isMove = false;
        if (srcHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        if (moveListener != null) {
            isMove = moveListener.onItemMove(srcHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return isMove;
    }

    // 侧滑时调用
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (moveListener != null) {
            moveListener.onItemRemove(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // 判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorAccent));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 恢复
        viewHolder.itemView.setBackgroundColor(Color.WHITE);

        // 恢复侧滑动画效果
        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleX(1);
        viewHolder.itemView.setScaleY(1);
        super.clearView(recyclerView, viewHolder);
    }


    // 可以在这里去做特效
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // 透明度动画,dx为x轴的增量
            float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setScaleX(alpha);
            viewHolder.itemView.setScaleY(alpha);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
