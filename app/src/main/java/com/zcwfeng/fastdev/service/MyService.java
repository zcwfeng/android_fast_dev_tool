package com.zcwfeng.fastdev.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;


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
        public void onBefore(Request request,int id) {
            super.onBefore(request,id);
            Log.e("zcw", "loading...");
        }

        @Override
        public void onAfter(int id) {
            super.onAfter(id);
            Log.e("zcw", "Sample-okHttp");
//            OkHttpUtils
//                    .get()//
//                    .url("https://127.0.0.1:8090/url.m3u8")//
//                    .build()//
//                    .execute(new MyStringCallback());
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("zcw", "onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response,int id) {
            Log.e("zcw", "onResponse:" + response);
        }

        @Override
        public void inProgress(float progress,long l,int id) {
            Log.e("zcw", "inProgress:" + progress);
        }
    }


}
