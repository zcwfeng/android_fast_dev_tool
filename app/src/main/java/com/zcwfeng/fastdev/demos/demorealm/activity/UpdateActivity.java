package com.zcwfeng.fastdev.demos.demorealm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.util.RealmHelper;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;


public class UpdateActivity extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    EditText etName;

    private RealmHelper mRealmHelper;
    private String mId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_update);
        initView();

        setToolbar(mToolbar, "改");

        initData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        etName = (EditText) findViewById(R.id.et_name);
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    private void initData() {
        mRealmHelper=new RealmHelper(this);
        mId=getIntent().getStringExtra("id");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name=etName.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(UpdateActivity.this,"请输入名称", Toast.LENGTH_SHORT).show();
                    return;
                }

                mRealmHelper.updateDog(mId,name);

                setResult(RESULT_OK);
                finish();
            break;
        }
    }
}
