package com.zcwfeng.fastdev.sample_okhttp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zcwfeng.componentlibs.surport.inject.ContentView;
import com.zcwfeng.componentlibs.surport.inject.ViewInject;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.httplibs.OkHttpUtils;
import com.zcwfeng.httplibs.callback.BitmapCallback;
import com.zcwfeng.httplibs.callback.FileCallBack;
import com.zcwfeng.httplibs.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ContentView(value = R.layout.activity_okhttp_main)
public class OkhttpUtilsMainActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, OkhttpUtilsMainActivity.class);
        context.startActivity(intent);
    }

    private String mBaseUrl = "http://192.168.56.1:8080/okHttpServer/";

    private static final String TAG = "MainActivity";

    @ViewInject(id = R.id.id_textview)
    private TextView mTv;
    @ViewInject(id = R.id.id_imageview)
    private ImageView mImageView;
    @ViewInject(id = R.id.id_progress)
    private ProgressBar mProgressBar;

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            setTitle("loading...");
        }

        @Override
        public void onAfter() {
            super.onAfter();
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Request request, Exception e) {
            mTv.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            mTv.setText("onResponse:" + response);
        }

        @Override
        public void inProgress(float progress) {
            Log.e(TAG, "inProgress:" + progress);
            mProgressBar.setProgress((int) (100 * progress));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressBar.setMax(100);
    }

    public void getHtml(View view) {
        String url = "http://www.csdn.net/";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());

    }

    public void postString(View view) {
        String url = mBaseUrl + "user!postString";
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(new User("zhy", "123")))
                .build()
                .execute(new MyStringCallback());

    }

    public void postFile(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(OkhttpUtilsMainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = mBaseUrl + "user!postFile";
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(new MyStringCallback());

    }

    public void getUser(View view) {
        String url = mBaseUrl + "user!getUser";
        OkHttpUtils
                .get()//
                .url(url)//
                .addParams("username", "hyman")//
                .addParams("password", "123")//
                .build()//
                .execute(new UserCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(User response) {
                        mTv.setText("onResponse:" + response.username);
                    }
                });
    }


    public void getUsers(View view) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "zhy");
        String url = mBaseUrl + "user!getUsers";
        OkHttpUtils//
                .post()//
                .url(url)//
//                .params(params)//
                .build()//
                .execute(new ListUserCallback()//
                {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(List<User> response) {
                        mTv.setText("onResponse:" + response);
                    }
                });
    }


    public void getHttpsHtml(View view) {
        String url = "https://kyfw.12306.cn/otn/";

        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(new MyStringCallback());

    }

    public void getImage(View view) {
        mTv.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }


    public void uploadFile(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(OkhttpUtilsMainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        String url = mBaseUrl + "user!uploadFile";

        OkHttpUtils.post()//
                .addFile("mFile", "messenger_01.png", file)//
                .url(url)//
                .params(params)//
                .headers(headers)//
                .build()//
                .execute(new MyStringCallback());
    }


    public void multiFileUpload(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "test1.txt");
        if (!file.exists()) {
            Toast.makeText(OkhttpUtilsMainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        String url = mBaseUrl + "user!uploadFile";
        OkHttpUtils.post()//
                .addFile("mFile", "messenger_01.png", file)//
                .addFile("mFile", "test1.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }


    public void downloadFile(View view) {
        String url = "https://github.com/hongyangAndroid/okhttp-utils/blob/master/gson-2.2.1.jar?raw=true";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar")//
                {

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void inProgress(float progress) {
                        mProgressBar.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        OkHttpUtils.cancelTag(this);
    }
}
