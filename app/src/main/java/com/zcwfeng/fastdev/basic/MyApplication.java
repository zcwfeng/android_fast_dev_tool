package com.zcwfeng.fastdev.basic;

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

    public static MyApplication getInstance() {
        return mApplacation;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplacation = this;
//        Firebase.setAndroidContext(this);
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

    }

}
