package com.example.administrator.testapp.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.testapp.R;
import com.example.administrator.testapp.adapter.DividerItemDecoration;
import com.example.administrator.testapp.adapter.MyItemTouchHelperCallback;
import com.example.administrator.testapp.adapter.MyRecyclerViewAdapter;
import com.example.administrator.testapp.adapter.StartDragListener;

import java.util.ArrayList;
import java.util.List;

public class DesignActivity extends AppCompatActivity implements StartDragListener {

    private RecyclerView mRecyclerView;
    private List<String> datas;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        initDatas();

        // 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DesignActivity.this));


        // 设置适配器
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(datas,this);


        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        myRecyclerViewAdapter.setOnItemClickLisener(new MyRecyclerViewAdapter.OnItemClickLisener() {
            @Override
            public void onClick(View view, int postion) {

                Toast.makeText(DesignActivity.this, "点击了第" + postion, Toast.LENGTH_SHORT).show();
            }
        });

        MyItemTouchHelperCallback itemTouchCallback = new MyItemTouchHelperCallback(myRecyclerViewAdapter);
        // 条目触摸帮助类
        helper = new ItemTouchHelper(itemTouchCallback);
        helper.attachToRecyclerView(mRecyclerView);


    }

    private void initDatas() {
        datas = new ArrayList<String>();
        datas.add("Android");
        datas.add("Java");
        datas.add("JavaScript");
        datas.add("C/C++");
        datas.add("Kotlin");
        datas.add("PHP");

        for (int i = 0; i < 20; i++) {
            datas.add("测试数据---" + (i + 1));
        }
    }

    @Override
    public void dragListener(RecyclerView.ViewHolder holder) {
        helper.startDrag(holder);
    }
}
