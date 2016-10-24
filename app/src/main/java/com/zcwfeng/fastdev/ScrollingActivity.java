package com.zcwfeng.fastdev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.componentlibs.surport.utils.AntiEmulator;
import com.zcwfeng.componentlibs.surport.utils.SystemUtils;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity_deprecated;
import com.zcwfeng.fastdev.BindingData.MvvmDemoActivity;
import com.zcwfeng.fastdev.MediaSample.MediaLibUseDemo;
import com.zcwfeng.fastdev.binder.AIDLTestActivity;
import com.zcwfeng.fastdev.flyrefresh.FlyRefreshActivity;
import com.zcwfeng.fastdev.glide.GlideLibDemos;
import com.zcwfeng.fastdev.intent_ref.IntentReferenceActivity;
import com.zcwfeng.fastdev.renderscript.RenderScriptTestActivity;
import com.zcwfeng.fastdev.sample_okhttp.OkhttpUtilsMainActivity;
import com.zcwfeng.fastdev.secure.skb.ExamplesRSA_DESActivity;
import com.zcwfeng.fastdev.ui.activity.CoordinatorLayoutTestActivity;
import com.zcwfeng.fastdev.ui.activity.DampingScrollActivity;
import com.zcwfeng.fastdev.ui.activity.HttpRequestStudyActivity;
import com.zcwfeng.fastdev.ui.activity.ImageRefViewTestActivity;
import com.zcwfeng.fastdev.ui.activity.NDKDemoActivity;
import com.zcwfeng.fastdev.ui.activity.RefreshTestActivity;
import com.zcwfeng.fastdev.ui.activity.SlideHRecyclerViewTestActivity;
import com.zcwfeng.fastdev.ui.activity.UserProfileActivity;
import com.zcwfeng.fastdev.widgettest.TestWidgetActivity;

public class ScrollingActivity extends BaseActivity_deprecated {

    //自定义注解开发。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        systemTest();

//        try {
//            firebase();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void firebase() {

        Firebase myFirebaseRef = new Firebase("https://zcwblog.firebaseio.com/");
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(FirebaseError error) { }
        });


//        myFirebaseRef.createUser("zcwfeng@google.com", "qwly_2001_Google", new Firebase.ValueResultHandler<Map<String, Object>>() {
//            @Override
//            public void onSuccess(Map<String, Object> result) {
//                System.out.println("Successfully created user account with uid: " + result.get("uid"));
//            }
//            @Override
//            public void onError(FirebaseError firebaseError) {
//                // there was an error
//            }
//        });

        myFirebaseRef.authWithPassword("zcwfeng@google.com", "qwly_2001_Google", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                Log.e("firebase",firebaseError.getDetails());
            }
        });
    }

    private void systemTest() {
        if (SystemUtils.isEmulator(this)) {
            Toast.makeText(this, "这个是模拟器", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "isEmulator判断漏洞", Toast.LENGTH_LONG).show();

        }


//        BluetoothAdapter
//                mAdapter= BluetoothAdapter.getDefaultAdapter();
//        if(mAdapter ==null){
//            Toast.makeText(this,"无蓝牙-这个是模拟器",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this,"isEmulator判断是手机",Toast.LENGTH_LONG).show();
//
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void doTest(View view) {
        switch (view.getId()) {
            case R.id.to_test_okhttp:
                OkhttpUtilsMainActivity.launch(this);
                break;
            case R.id.media_sample:
                MediaLibUseDemo.launch(this, MediaLibUseDemo.class);
                break;
            case R.id.user_profile_common_Activity:
                UserProfileActivity.launch(this, UserProfileActivity.class);
                break;
            case R.id.calendar_test:

                boolean isEmu = AntiEmulator.CheckDeviceIDS(BaseApplication.getInstance())
                        || AntiEmulator.checkPipes()
                        || AntiEmulator.CheckEmulatorBuild(BaseApplication.getInstance())
                        || AntiEmulator.CheckEmulatorFiles()
                        || AntiEmulator.CheckImsiIDS(BaseApplication.getInstance())
                        || AntiEmulator.CheckOperatorNameAndroid(BaseApplication.getInstance())
                        || AntiEmulator.CheckPhoneNumber(BaseApplication.getInstance())
                        || AntiEmulator.checkQEmuDriverFile();

                Toast.makeText(ScrollingActivity.this, "--" + String.valueOf(isEmu) + "----", Toast.LENGTH_SHORT).show();
                TestWidgetActivity.launch(ScrollingActivity.this);

                break;
            case R.id.intent_test:
                IntentReferenceActivity.launch(this);
                break;
            case R.id.coordnator_layout_btn:
                CoordinatorLayoutTestActivity.launch(ScrollingActivity.this, CoordinatorLayoutTestActivity.class);
                break;
            case R.id.testRefreshTestActivity:
                RefreshTestActivity.launch(ScrollingActivity.this);
                break;
            case R.id.ndkdemo:
                NDKDemoActivity.launch(ScrollingActivity.this);
                break;
            case R.id.render_script_btn:
                RenderScriptTestActivity.launch(ScrollingActivity.this);
                break;
            case R.id.custom_image_ref:
                ImageRefViewTestActivity.launch(this);
                break;
            case R.id.new_yyt_video:
                MediaLibUseDemo.launch(this, MediaLibUseDemo.class);
                break;
            case R.id.binding_data_mvvm:
                MvvmDemoActivity.launch(this);
                break;
            case R.id.custom_recyclerview:
                SlideHRecyclerViewTestActivity.launch(ScrollingActivity.this, SlideHRecyclerViewTestActivity.class);
                break;
            case R.id.http_request:
                HttpRequestStudyActivity.launch(ScrollingActivity.this);
                break;
            case R.id.flyrefresh:
                FlyRefreshActivity.launch(ScrollingActivity.this);
                break;
            case R.id.skb_activity:
                ExamplesRSA_DESActivity.launch(ScrollingActivity.this);
                break;
            case R.id.glidelib_activity:
                GlideLibDemos.launch(ScrollingActivity.this);
                break;
            case R.id.binder_activity:
                AIDLTestActivity.launch(ScrollingActivity.this);
                break;
            case R.id.damping_scroll:
                launch(ScrollingActivity.this, DampingScrollActivity.class);
                break;
            case R.id.store_start:
                /**
                 * http://market.android.com/details?id=<java包名>
                 * 或者
                 * market://details?id=<java包名>
                 *
                 * @param view
                 */
                //        Uri uri = Uri.parse("market://details?id=" + "com.yinyuetai.ui");
                //        Uri uri = Uri.parse("market://search?q=pname:" + "com.yinyuetai.ui");
                //        Uri uri = Uri.parse("market://search?q=pub:" + "音悦台");
                //        Uri uri = Uri.parse("market://search?q=" + "音悦台");
                Uri uri = Uri.parse("market://search?q=音 悦 pub:" + "音悦台");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

}
