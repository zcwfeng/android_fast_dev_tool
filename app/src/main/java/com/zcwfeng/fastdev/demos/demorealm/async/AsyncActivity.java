package com.zcwfeng.fastdev.demos.demorealm.async;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;


public class AsyncActivity extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_async);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        findViewById(R.id.btn_add_delete).setOnClickListener(this);
        findViewById(R.id.btn_update_query).setOnClickListener(this);
        setToolbar(mToolbar,"异步操作");
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_add_delete:
                startActivity(new Intent(AsyncActivity.this,AddDeleteActivity.class));
                break;
            case R.id.btn_update_query:
                startActivity(new Intent(AsyncActivity.this,AsyncQueryActivity.class));
                break;
            default:
                break;

        }

    }
}
