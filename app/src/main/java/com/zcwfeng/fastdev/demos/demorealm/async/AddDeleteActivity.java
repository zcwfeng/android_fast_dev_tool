package com.zcwfeng.fastdev.demos.demorealm.async;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.adapter.AsyncCatAdapter;
import com.zcwfeng.fastdev.demos.demorealm.bean.Cat;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class AddDeleteActivity extends BaseActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;

    private List<Cat> mCats=new ArrayList<>();
    private String[] letters=new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","U","X","Y","Z"};
    private String[] letters1=new String[]{"a","c","u","p","q","y"};
    private AsyncCatAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_add_delete);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setToolbar(mToolbar,"异步增、删");
        initData();
        setRecyclerView();
    }

    private void setRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter=new AsyncCatAdapter(this,mCats,R.layout.item_dog);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i=0;i<15;i++){
            Cat cat=new Cat();
            String name=letters[i]+letters1[i%5]+letters1[i%3];
            cat.setName(name);
            cat.setAge(i%4);
            cat.setId("10"+i);
            mCats.add(cat);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.CancelTask();
    }
}
