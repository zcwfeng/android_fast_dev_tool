package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity_deprecated;
import com.zcwfeng.fastdev.R;

/**
 * Created by zcw on 16/8/24.
 */

public class NewFutureApi24Activity extends BaseActivity_deprecated {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfuture_api24);
    }


    @Override
    protected void onResume() {
        prntLog("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        prntLog("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        prntLog("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        prntLog("onDestory");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        prntLog("onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        prntLog("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }


    private void prntLog(String log) {
        Log.d("MainActivity", log);
    }
}
