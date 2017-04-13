package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.util.Log;


public class LifeCycleActivity extends BaseActivity {
    final String TAG = LifeCycleActivity.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Log.e(TAG,"onCreate,he activity is being created");
    }
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.e(TAG,"onStart,The activity is about to become visible");

    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.e(TAG,"onResume,The activity has become visible (it is now \"resumed\")");

    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.e(TAG,"onPause,Another activity is taking focus (this activity is about to be \"paused\"");

    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.e(TAG,"onStop,The activity is no longer visible (it is now \"stopped\"");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.e(TAG,"onDestroy,The activity is about to be destroyed.");

    }
}