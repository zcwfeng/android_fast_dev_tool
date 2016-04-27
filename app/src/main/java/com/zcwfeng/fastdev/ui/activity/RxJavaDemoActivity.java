package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.data.WeatherData;
import com.zcwfeng.fastdev.rxjava.ApiManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by David.zhang on 16/4/26.
 * Description：
 */
public class RxJavaDemoActivity extends BaseActivity {
    private static final String LOG_TAG = RxJavaDemoActivity.class.getSimpleName();

    private static final String[] CITIES = {"Budapest,hu"};

    public static void launch(Context from){
        Intent intent = new Intent(from,RxJavaDemoActivity.class);
        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);

        /**
         * 多个 city 请求
         * map，flatMap 对 Observable进行变换
         */
        Observable.from(CITIES).flatMap(new Func1<String, Observable<WeatherData>>() {
            @Override
            public Observable<WeatherData> call(String s) {
                return ApiManager.getWeatherData(s);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/*onNext*/new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        Log.d(LOG_TAG, weatherData.toString());
                    }
                }, /*onError*/new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

        /**
         * 单个 city 请求
         */
        ApiManager.getWeatherData(CITIES[0]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        Log.d(LOG_TAG, weatherData.toString());
                        ((TextView) findViewById(R.id.text)).setText(weatherData.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(LOG_TAG, throwable.getMessage(), throwable);
                    }
                });

        /**
         * Android View 事件处理
         */
        RxView.clicks(findViewById(R.id.text))
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {

                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Progress", Toast.LENGTH_SHORT).show();//主线程
                    }
                });

        Observable.just("a", "b")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
                        Toast.makeText(getApplicationContext(), "Progress", Toast.LENGTH_SHORT).show();//主线程
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())//指定doOnSubscribe在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
                    }
                });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
