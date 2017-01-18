package com.zcwfeng.fastdev.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.zcwfeng.fastdev.R;

/**
 * @author zcw
 */
public class BaseActivity extends AppCompatActivity {


    private static String[] PERMISSIONS_EXTERNAL = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final int REQUEST_EXTERNAL = 110;
    public static final int REQUEST_CAMERA = 111;
    public static final int REQUEST_PHONE_STATE = 112;
    public static final int REQUEST_RECORD_AUDIO = 113;

    public static void launch(Context context,Class<?> clazz){
        Intent intent = new Intent();
        intent.setClass(context,clazz);
        context.startActivity(intent);
    }

    public void requestREAD_PHONE_STATEPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_PHONE_STATE);
        } else {
            onGetREAD_PHONE_STATEPermissions();
        }
    }

    public void requestREAD_EXTERNAL_STORAGEPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_EXTERNAL, REQUEST_EXTERNAL);
        } else {
            onGetEXTERNALPermissions();
        }
    }

    public void requestRECORD_AUDIOPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO);
        } else {
            onGetRECORD_AUDIOPermissions();
        }
    }


    public void requestCAMERAPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_EXTERNAL);
        } else {
            onGetCAMERAPermissions();
        }
    }


    public void onGetCAMERAPermissions() {

    }

    public void onGetEXTERNALPermissions() {

    }

    public void onGetRECORD_AUDIOPermissions() {

    }

    public void onGetREAD_PHONE_STATEPermissions() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    protected void setToolbar(Toolbar toolbar, String title){
        if(toolbar == null) {
            Toolbar mToolBar = (Toolbar) findViewById(R.id.toolBar);
            String str = TextUtils.isEmpty(title)?"快速开发库":title;
            setToolbar(mToolBar,str);
        }else {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }





    }


    protected void setLayoutId(int id) {
        setContentView(id);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            MobclickAgent.onResume(BaseActivity.this);
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            MobclickAgent.onPause(BaseActivity.this);
        } catch (Exception ignored) {
        }
    }
}
