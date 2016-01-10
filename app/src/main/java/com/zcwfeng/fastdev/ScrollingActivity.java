package com.zcwfeng.fastdev;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zcwfeng.componentlibs.surport.inject.ContentView;
import com.zcwfeng.componentlibs.ui.BaseActivity;
import com.zcwfeng.fastdev.BindingData.MvvmDemoActivity;
import com.zcwfeng.fastdev.MediaSample.MediaLibUseDemo;
import com.zcwfeng.fastdev.sample_okhttp.OkhttpUtilsMainActivity;

@ContentView(value = R.layout.activity_scrolling)
public class ScrollingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toSampleOkhttp(View v) {
        OkhttpUtilsMainActivity.launch(this);
    }

    public void doMvvmDemo(View v) {
        MvvmDemoActivity.launch(this);
    }

    public void doMediaSample(View view) {
        MediaLibUseDemo.launch(this, null);
    }
}
