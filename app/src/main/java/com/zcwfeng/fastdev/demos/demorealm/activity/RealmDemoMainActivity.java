package com.zcwfeng.fastdev.demos.demorealm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.async.AsyncActivity;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;


/**
 * 主界面
 */
public class RealmDemoMainActivity extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_realm_main);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitle("DemoRealm");
        setSupportActionBar(mToolbar);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_async).setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(RealmDemoMainActivity.this,DogListActivity.class));
                break;
            case R.id.btn_query:
                startActivity(new Intent(RealmDemoMainActivity.this,QueryActivity.class));
                break;
            case R.id.btn_async:
                startActivity(new Intent(RealmDemoMainActivity.this,AsyncActivity.class));

                break;
            default:
                break;
        }
    }
}
