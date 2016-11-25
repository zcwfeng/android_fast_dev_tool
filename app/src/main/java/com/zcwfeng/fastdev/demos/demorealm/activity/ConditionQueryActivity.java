package com.zcwfeng.fastdev.demos.demorealm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.adapter.LikeDogAdapter;
import com.zcwfeng.fastdev.demos.demorealm.bean.Dog;
import com.zcwfeng.fastdev.demos.demorealm.util.RealmHelper;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 条件查询
 */
public class ConditionQueryActivity extends BaseActivity implements View.OnClickListener {
    EditText etId;
    EditText etAge;
    RecyclerView mRecyclerView;
    Toolbar mToolbar;

    private RealmHelper mRealmHelper;
    private List<Dog> mDogs = new ArrayList<>();
    private LikeDogAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_condition_query);

        setToolbar(mToolbar, "条件查询");
        initView();
        initData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        etAge = (EditText) findViewById(R.id.et_age);
        etId = (EditText) findViewById(R.id.et_id);

        findViewById(R.id.btn_query_id).setOnClickListener(this);
        findViewById(R.id.btn_query_age).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query_id:
                queryById();
                break;
            case R.id.btn_query_age:
                queryByAge();
                break;
            default:
                break;
        }

    }

    private void initData() {
        mRealmHelper = new RealmHelper(this);
        mAdapter = new LikeDogAdapter(this, mDogs, R.layout.item_dog);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void queryById() {
        String id = etId.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(ConditionQueryActivity.this, "请输入Id", Toast.LENGTH_SHORT).show();
            return;
        }

        mDogs.clear();
        Dog dog = mRealmHelper.queryDogById(id);
        if (dog != null) {
            mDogs.add(dog);

        } else {
            Toast.makeText(ConditionQueryActivity.this, "查询结果为空", Toast.LENGTH_SHORT).show();
        }
        mAdapter.notifyDataSetChanged();

    }

    private void queryByAge() {
        String age = etAge.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(ConditionQueryActivity.this, "请输入age", Toast.LENGTH_SHORT).show();
            return;
        }

        mDogs.clear();
        List<Dog> dogs = mRealmHelper.queryDobByAge(Integer.parseInt(age));
        if (dogs != null) {
            mDogs.addAll(dogs);

        } else {
            Toast.makeText(ConditionQueryActivity.this, "查询结果为空", Toast.LENGTH_SHORT).show();

        }
        mAdapter.notifyDataSetChanged();
    }

}
