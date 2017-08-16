package com.zcwfeng.fastdev.basic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.baidu.mapapi.SDKInitializer;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.facebook.FacebookSdk;
import com.flurry.android.FlurryAgent;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.tencent.smtt.sdk.QbSdk;
import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.fastdev.demos.demo_greendao.bean.DaoMaster;
import com.zcwfeng.fastdev.demos.demo_greendao.bean.DaoSession;
import com.zcwfeng.fastdev.demos.demorealm.util.RealmHelper;
import com.zcwfeng.fastdev.ui.fragment.download.ImageAdapter;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by David.zhang on 16/3/18.
 * Description：
 */
public class MyApplication extends BaseApplication{
    static MyApplication mApplacation;
    private static DaoSession daoSession;

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
        Fabric.with(this, new Crashlytics());

        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "S8BWRCDF8QPH62NC5Z5F");
        mApplacation = this;

        //初始化屏幕宽高
        getScreenSize();

//        Firebase.setAndroidContext(this);
        Fabric.with(this, new Crashlytics(), new Answers());


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
        QbSdk.initX5Environment(getApplicationContext(), cb);


        // Realm Database Init
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        //初始化百度
        SDKInitializer.initialize(getApplicationContext());


        //初始化weex
        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this, config);

        //配置数据库
        setupDatabase();


        FacebookSdk.sdkInitialize(getApplicationContext());

//        AppEventsLogger.activateApp(this.getApplicationContext(),"1860446400872605");
//        AdSettings.addTestDevice("d3439e8dea48a56c4095d25f037a3fdd");
//        SDK.startSDK(this,33404,"475239399d1018409c3a5afb6a1d379f");


    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

}
