package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcwfeng.fastdev.R;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zcw on 2017/3/17.
 */

public class MySecurityActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MySecurityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_layout);
        setToolbar(null,"快速开发库");

        try {
            java.lang.Process process = java.lang.Runtime.getRuntime().exec("su");
            OutputStream os = process.getOutputStream();
            os.write("ls /system/app".getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
