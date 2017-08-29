package com.example.administrator.testapp.adapter;

/**
 * Created by Administrator on 2017/8/24.
 */

public interface ItemTouchMoveListener {

    /**
     * <p>拖拽时调用</p>
     * 可以在方法里面实现：拖拽条目并实现刷新效果
     *
     * @param fromPostion 开始位置
     * @param toPosition  结束位置
     * @return
     */
    public boolean onItemMove(int fromPostion, int toPosition);

    /**
     * <p>当条目被移除时回调</p>
     * @param postion 移除的位置
     * @return
     */
    public boolean onItemRemove(int postion);

}
