/*****************Copyright (C), 2010-2015, FORYOU Tech. Co., Ltd.********************/
package com.zcwfeng.fastdev.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @Filename: AIDLTestService.java
 * @Author: slcao
 * @CreateDate: 2011-5-16
 * @Description: description of the new class
 * @Others: comments
 * @ModifyHistory:
 */
public class AIDLTestService  extends Service {

    private IActivityAidl callback;


    private void Log(String str) {
        Log.d("AIDL", "------ " + str + "------");
    }


    @Override
    public void onCreate() {
        Log("service create");
    }


    @Override
    public void onStart(Intent intent, int startId) {
        Log("service start id=" + startId);
    }


    @Override
    public IBinder onBind(Intent t) {
        Log("service on bind");
        return mBinder;
    }


    @Override
    public void onDestroy() {
        Log("service on destroy");
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log("service on unbind");
        return super.onUnbind(intent);
    }


    public void onRebind(Intent intent) {
        Log("service on rebind");
        super.onRebind(intent);
    }

    private final IServiceAidl.Stub mBinder = new IServiceAidl.Stub() {

        @Override
        public void invokCallBack() throws RemoteException {
            Log("AIDLService.invokCallBack");
            Rect rect = new Rect();
            rect.bottom = -1;
            rect.left = -1;
            rect.right = 1;
            rect.top = 1;
//            callback.performAction(rect);
            callback.performAction();

        }


        @Override
        public void registerTestCall(IActivityAidl cb) throws RemoteException {
            Log("AIDLService.registerTestCall");
            callback = cb;
        }
    };
}
