package com.zcwfeng.fastdev.demos.demorealm.async;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.bean.Cat;
import com.zcwfeng.fastdev.demos.demorealm.util.ToastUtil;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class UpdateCatActivity extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    EditText etName;
    private Realm mRealm;
    private String mId;
    private RealmAsyncTask updateTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setLayoutId(R.layout.activity_update_cat);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        etName = (EditText) findViewById(R.id.et_name);
        findViewById(R.id.btn_update).setOnClickListener(this);
        initData();

    }

    private void initData() {
        setToolbar(mToolbar,"异步更新");
        mId=getIntent().getStringExtra("id");
        mRealm= Realm.getDefaultInstance();

    }

    @Override
    public void onClick(View view){
        final String name=etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtil.showShortToast(UpdateCatActivity.this,"请输入新的Name");
            return;
        }

     updateTask=   mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cat cat=realm.where(Cat.class).equalTo("id",mId).findFirst();
                cat.setName(name);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                ToastUtil.showShortToast(UpdateCatActivity.this,"更新成功");
                setResult(RESULT_OK);
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                ToastUtil.showShortToast(UpdateCatActivity.this,"失败成功");

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updateTask!=null&&!updateTask.isCancelled()){
            updateTask.cancel();
        }
    }
}
