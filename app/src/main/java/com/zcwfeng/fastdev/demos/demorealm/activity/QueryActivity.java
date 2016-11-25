package com.zcwfeng.fastdev.demos.demorealm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;


/**
 *  查、改
 */
public class QueryActivity extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_query);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setToolbar(mToolbar,"改、查");

        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_condition_query).setOnClickListener(this);
        findViewById(R.id.btn_other_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query:
                startActivity(new Intent(QueryActivity.this,AllDogActivity.class));
                break;
            case R.id.btn_condition_query:
                startActivity(new Intent(QueryActivity.this,ConditionQueryActivity.class));
                break;
            case R.id.btn_other_query:
                startActivity(new Intent(QueryActivity.this,OtherQueryActivity.class));
                break;
            default:
                break;

        }

    }
}
