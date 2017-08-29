package com.example.administrator.testapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.testapp.R;

import java.util.Collections;
import java.util.List;

/**
 * 适配器
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements ItemTouchMoveListener {

    private final StartDragListener mStartDragListener;
    private List<String> datas;
    private OnItemClickLisener lisener;

    public MyRecyclerViewAdapter(List<String> datas, StartDragListener listener) {
        this.datas = datas;
        this.mStartDragListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建ViewHolder
//        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
        MyViewHolder viewHolder = new MyViewHolder(View.inflate(parent.getContext(), R.layout.item_view, null));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // 绑定数据
        holder.tv.setText(datas.get(position));

        if (lisener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lisener.onClick(v, holder.getLayoutPosition());
                }
            });
        }

        if (mStartDragListener != null) {
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mStartDragListener.dragListener(holder);
                    }
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public boolean onItemMove(int fromPostion, int toPosition) {
        // 1、交换数据
        Collections.swap(datas, fromPostion, toPosition);
        // 2、刷新界面
        notifyItemMoved(fromPostion, toPosition);
        return false;
    }

    @Override
    public boolean onItemRemove(int postion) {
        // 1、删除数据
        datas.remove(postion);
        // 2、刷新界面
        notifyItemRemoved(postion);
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    public interface OnItemClickLisener {
        void onClick(View view, int postion);
    }

    public void setOnItemClickLisener(OnItemClickLisener lisener) {
        this.lisener = lisener;
    }


}
