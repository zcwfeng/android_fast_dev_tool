package com.zcwfeng.fastdev.demos.demorealm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.bean.Dog;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

import io.realm.Realm;

/**
 * 其他查询
 */
public class OtherQueryActivity extends BaseActivity {
    Toolbar mToolbar;

    TextView tvAverage;//平均年龄
    TextView tvSumAge;//总年龄
    TextView tvMaxId;

    private Realm mRealm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_other_query);

        initView();

        setToolbar(mToolbar, "其他查询");

        mRealm = Realm.getDefaultInstance();

        getAverageAge();

        getSumAge();

        getMaxId();
    }

    private void initView() {
        tvMaxId = (TextView) findViewById(R.id.tv_max_id);
        tvSumAge = (TextView) findViewById(R.id.tv_sum_age);
        tvAverage = (TextView) findViewById(R.id.tv_average_age);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
    }

    /**
     * 查询平均年龄
     */
    private void getAverageAge() {
        double avgAge = mRealm.where(Dog.class).findAll().average("age");
        tvAverage.setText(avgAge + "岁");
    }

    /**
     * 查询总年龄
     */
    private void getSumAge() {
        Number sum = mRealm.where(Dog.class).findAll().sum("age");
        int sumAge = sum.intValue();
        tvSumAge.setText(sumAge + "岁");
    }

    /**
     * 查询最大年龄
     */
    private void getMaxId() {
        Number max = mRealm.where(Dog.class).findAll().max("age");
        int maxAge = max.intValue();
        tvMaxId.setText(maxAge + "岁");
    }
}
