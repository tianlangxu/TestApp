package com.example.administrator.testapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/23.
 */

public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<View> mHeaderViewInfos;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<View> mFooterViewInfos;

    private ArrayList<View> EMPTY_INFO_LIST = new ArrayList<View>();


    public HeaderViewRecyclerAdapter(ArrayList<View> mHeaderViewInfos, ArrayList<View> mFooterViewInfos, RecyclerView.Adapter mAdapter) {
        if (mHeaderViewInfos == null) {
            this.mHeaderViewInfos = EMPTY_INFO_LIST;
        } else {
            this.mHeaderViewInfos = mHeaderViewInfos;
        }

        if (mFooterViewInfos == null) {
            this.mFooterViewInfos = EMPTY_INFO_LIST;
        } else {
            this.mFooterViewInfos = mFooterViewInfos;
        }

        this.mAdapter = mAdapter;
    }

    public int getHeaderCount() {
        return mHeaderViewInfos.size();
    }

    public int getFooterCount() {
        return mFooterViewInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeaderCount();
        if (position < numHeaders) { // 表示是头部
            return RecyclerView.INVALID_TYPE;
        }
        // 正常条目部分
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }

        // footer部分
        return RecyclerView.INVALID_TYPE - 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Header (negative positions will throw an IndexOutOfBoundsException)
        if (viewType == RecyclerView.INVALID_TYPE) {//Header
            return new HeaderViewHodler(mHeaderViewInfos.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {//Footer
            return new HeaderViewHodler(mFooterViewInfos.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeaderCount();
        if (position < numHeaders) { // 表示是头部
            return;
        }
        // 正常条目部分
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder,adjPosition);
                return;
            }
        }

        // footer部分
//        return;
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return mAdapter.getItemCount() + getHeaderCount() + getFooterCount();
        } else {
            return getHeaderCount() + getFooterCount();
        }
    }

    private static class HeaderViewHodler extends RecyclerView.ViewHolder {

        public HeaderViewHodler(View itemView) {
            super(itemView);
        }
    }


}
