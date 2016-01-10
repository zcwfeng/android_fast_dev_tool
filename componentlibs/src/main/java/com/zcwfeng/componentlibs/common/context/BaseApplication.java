package com.zcwfeng.componentlibs.common.context;

import android.app.Application;

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
public class BaseApplication extends Application {
    public static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static BaseApplication getInstance(){
        return app;
    }
}
