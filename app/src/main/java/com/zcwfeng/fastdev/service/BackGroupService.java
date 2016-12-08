package com.zcwfeng.fastdev.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zcw on 2016/12/8.
 */

public class BackGroupService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Service","onBind");

        return new MyBinder();
    }

    public class MyBinder extends Binder{
        public void showToast(){
            Log.e("Service","showToast");
        }

        public void showList(){
            Log.e("Service","showList");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "onStartCommand");
        //这里执行耗时操作
        new Thread() {
            @Override
            public void run() {
                while (true){
                    try {
                        Log.e("Service", "doSomething");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service","onUnbind");

        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service","onCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Service","onDestroy");

    }
}
