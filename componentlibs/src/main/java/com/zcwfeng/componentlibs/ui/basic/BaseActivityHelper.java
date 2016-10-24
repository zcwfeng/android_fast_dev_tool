package com.zcwfeng.componentlibs.ui.basic;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

public class BaseActivityHelper {

    private BaseActivity_deprecated mActivity;

    protected void bindActivity(BaseActivity_deprecated activity) {
        this.mActivity = activity;
    }

    protected BaseActivity_deprecated getActivity() {
        return mActivity;
    }

    protected void onCreate(Bundle savedInstanceState) {

    }

    public void onPostCreate(Bundle savedInstanceState) {

    }


    protected void onStart() {

    }

    protected void onRestart() {

    }

    protected void onResume() {

    }

    protected void onPause() {

    }

    protected void onStop() {

    }

    public void onDestroy() {

    }

    public void finish() {

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onSaveInstanceState(Bundle outState) {

    }


    protected boolean onHomeClick() {
        return false;
    }

    public boolean onBackClick() {
        return false;
    }

    protected int configTheme() {
        return 0;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}