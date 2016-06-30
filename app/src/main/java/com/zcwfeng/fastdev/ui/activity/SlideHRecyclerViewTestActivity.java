package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.widget.recyclerview.RecyclerAdapter;
import com.zcwfeng.fastdev.ui.widget.recyclerview.SlideHRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SlideHRecyclerViewTestActivity extends BaseActivity {


    private List<Integer> lists = new ArrayList<>();
    private SlideHRecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideh_recyclerview);
        initData();
        initviews();
    }

    private void initviews() {
        recyclerView = (SlideHRecyclerView) findViewById(R.id.my_recycler);
        adapter = new RecyclerAdapter(getApplicationContext(), lists);
        manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 1; i < 20; i++) {
            lists.add(i);
        }
    }


}
