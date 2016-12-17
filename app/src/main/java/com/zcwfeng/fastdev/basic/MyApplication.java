package com.zcwfeng.fastdev.basic;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.smtt.sdk.QbSdk;
import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.fastdev.demos.demorealm.util.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by David.zhang on 16/3/18.
 * Description：
 */
public class MyApplication extends BaseApplication {
    static MyApplication mApplacation;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static MyApplication getInstance() {
        return mApplacation;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplacation = this;


        //初始化屏幕宽高
        getScreenSize();

//        Firebase.setAndroidContext(this);




        // init x5 WebView
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                Log.e("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub

            }
        };
        QbSdk.initX5Environment(getApplicationContext(),cb);




        // Realm Database Init
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        //初始化百度
        SDKInitializer.initialize(getApplicationContext());


    }



    private void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

}
