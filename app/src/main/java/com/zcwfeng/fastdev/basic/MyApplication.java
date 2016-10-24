package com.zcwfeng.fastdev.basic;

import com.zcwfeng.componentlibs.BaseApplication;

/**
 * Created by David.zhang on 16/3/18.
 * Description：
 */
public class MyApplication extends BaseApplication {
    static MyApplication mApplacation;

    public static MyApplication getInstance() {
        return mApplacation;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplacation = this;
//        Firebase.setAndroidContext(this);

    }

}
