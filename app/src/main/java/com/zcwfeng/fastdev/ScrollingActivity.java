package com.zcwfeng.fastdev;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zcwfeng.componentlibs.surport.inject.ContentView;
import com.zcwfeng.componentlibs.surport.inject.ViewInject;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.BindingData.MvvmDemoActivity;
import com.zcwfeng.fastdev.MediaSample.MediaLibUseDemo;
import com.zcwfeng.fastdev.intent_ref.IntentReferenceActivity;
import com.zcwfeng.fastdev.sample_okhttp.OkhttpUtilsMainActivity;
import com.zcwfeng.fastdev.ui.activity.CoordinatorLayoutTestActivity;
import com.zcwfeng.fastdev.ui.activity.NDKDemoActivity;
import com.zcwfeng.fastdev.ui.activity.RefreshTestActivity;
import com.zcwfeng.fastdev.ui.activity.RxJavaDemoActivity;
import com.zcwfeng.fastdev.ui.activity.UserProfileActivity;
import com.zcwfeng.fastdev.widgettest.TestWidgetActivity;

@ContentView(value = R.layout.activity_scrolling)
public class ScrollingActivity extends BaseActivity {
    @ViewInject(id = R.id.to_test_okhttp)
    Button okHttp;
    @ViewInject(id = R.id.user_profile_common_Activity)
    Button userProfileActivity;
    @ViewInject(id = R.id.calendar_test)
    Button testCalendar;
    @ViewInject(id = R.id.intent_test)
    Button testIntentRef;
    @ViewInject(id = R.id.coordnator_layout_btn)
    Button testCoordnatorLayotu;
    @ViewInject(id = R.id.testRefreshTestActivity)
    Button testRefreshTestActivity;
    @ViewInject(id = R.id.ndkdemo)
    Button testNdkDemo;
    @ViewInject(id = R.id.rxjavademo)
    Button testRxJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
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
        MediaLibUseDemo.launch(this, MediaLibUseDemo.class);
    }

    public void toUserProfileActivity(View v) {
        UserProfileActivity.launch(this,UserProfileActivity.class);
    }

    public void doTestCalendar(View v){
        TestWidgetActivity.launch(this);
    }

    public void doTestIntent(View v){
        IntentReferenceActivity.launch(this);}

    public void doTestCoordnator(View v) {
        CoordinatorLayoutTestActivity.launch(ScrollingActivity.this,CoordinatorLayoutTestActivity.class);
    }


    public void doTestRefreshTestActivity(View v) {
        RefreshTestActivity.launch(ScrollingActivity.this);
    }
    public void testNdk(View v) {
        NDKDemoActivity.launch(ScrollingActivity.this);
    }

    public void doRxJava(View v) {
        RxJavaDemoActivity.launch(ScrollingActivity.this);
    }
}
