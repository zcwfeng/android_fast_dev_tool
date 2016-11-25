package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcwfeng.fastdev.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestREAD_PHONE_STATEPermissions();

        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                BaseActivity.launch(SplashActivity.this, MainActivity.class);
                finish();
            }
        }.sendEmptyMessageDelayed(0, 3000);

    }


    @Override
    public void onGetCAMERAPermissions() {
        super.onGetCAMERAPermissions();
    }

    @Override
    public void onGetEXTERNALPermissions() {
        super.onGetEXTERNALPermissions();
    }

    @Override
    public void onGetREAD_PHONE_STATEPermissions() {
        super.onGetREAD_PHONE_STATEPermissions();
        Glide.with(getApplicationContext()).load(getResources().getString(R.string.glide_single_img_uri_url)).into((ImageView) findViewById(R.id.splash_bg));

    }

    @Override
    public void onGetRECORD_AUDIOPermissions() {
        super.onGetRECORD_AUDIOPermissions();
    }
}
