package com.zcwfeng.fastdev.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.zcwfeng.httplibs.OkHttpUtils;
import com.zcwfeng.httplibs.callback.StringCallback;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();


        getHttpsM3U8();
    }

    public void getHttpsM3U8() {
//        String url = "https://127.0.0.1:8090/url.m3u8";
        String url = "http://127.0.0.1:8000";

        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(new MyStringCallback());


    }


    class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            Log.e("zcw", "loading...");
        }

        @Override
        public void onAfter() {
            super.onAfter();
            Log.e("zcw", "Sample-okHttp");
//            OkHttpUtils
//                    .get()//
//                    .url("https://127.0.0.1:8090/url.m3u8")//
//                    .build()//
//                    .execute(new MyStringCallback());
        }

        @Override
        public void onError(Request request, Exception e) {
            Log.e("zcw", "onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            Log.e("zcw", "onResponse:" + response);
        }

        @Override
        public void inProgress(float progress) {
            Log.e("zcw", "inProgress:" + progress);
        }
    }


}
