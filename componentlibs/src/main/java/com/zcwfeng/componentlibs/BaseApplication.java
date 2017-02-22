package com.zcwfeng.componentlibs;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zcwfeng.componentlibs.common.setting.SettingUtility;
import com.zcwfeng.componentlibs.surport.utils.SdcardUtils;
import com.zcwfeng.componentlibs.surport.utils.UIUtils;

import java.io.File;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * 1.初始化界面元素，分辨率等
 * 2.加载需要的库文件
 * 3.初始化必要的组件
 * 4.初始化数据库
 * 5.初始化service等（zixing,oss,环信，Fresco,Picasso,ImageLoader,okhttp,react,webview,videoview,surfaceview,notification）
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class BaseApplication extends MultiDexApplication{
    public static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //初始化Fresco库
        Fresco.initialize(this);

        // 初始化ActivityHelper
//        ActivityHelper.config(this);

        // 初始化设置
//        SettingUtility.setSettingUtility();

        //
        UIUtils.initScreenWH();

//        Logger.DEBUG = SettingUtility.getBooleanSetting("debug");

    }

    public static BaseApplication getInstance(){
        return app;
    }

//    static {
//        AppCompatDelegate.setDefaultNightMode(
//                AppCompatDelegate.MODE_NIGHT_YES);
//    }


    /**
     * 程序的文件目录，如果setting配置的是android，标志目录位于/sdcard/Application/PackageName/...下<br/>
     * 否则，就是/sdcard/setting[root_path]/...目录
     *
     * @return
     */
    public String getAppPath() {
        if ("android".equals(SettingUtility.getStringSetting("root_path")))
            return getExternalCacheDir().getAbsolutePath() + File.separator;

        return SdcardUtils.getSdcardPath() + File.separator + SettingUtility.getStringSetting("root_path") + File.separator;
    }


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
//        TinkerPatch.init(TinkerPatchApplicationLike.getTinkerPatchApplicationLike());
    }


}
