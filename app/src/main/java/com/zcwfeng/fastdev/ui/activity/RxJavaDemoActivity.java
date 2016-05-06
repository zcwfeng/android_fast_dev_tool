package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.data.WeatherData;
import com.zcwfeng.fastdev.rxjava.ApiManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
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

    public static void launch(Context from) {
        Intent intent = new Intent(from, RxJavaDemoActivity.class);
        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        rxJavaDemo1();
        rxJavaDemo2();
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


    public void languageDemo() {
        //1.hello world
        //=======
        // java
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                for (File folder : folders) {
//                    File[] files = folder.listFiles();
//                    for (File file : files) {
//                        if (file.getName().endsWith(".png")) {
//                            final Bitmap bitmap = getBitmapFromFile(file);
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    imageCollectorView.addImage(bitmap);
//                                }
//                            });
//                        }
//                    }
//                }
//            }
//        }.start();
        //=======
        // rx java
//        Observable.from(folders)
//                .flatMap(new Func1<File, Observable<File>>() {
//                    @Override
//                    public Observable<File> call(File file) {
//                        return Observable.from(file.listFiles());
//                    }
//                })
//                .filter(new Func1<File, Boolean>() {
//                    @Override
//                    public Boolean call(File file) {
//                        return file.getName().endsWith(".png");
//                    }
//                })
//                .map(new Func1<File, Bitmap>() {
//                    @Override
//                    public Bitmap call(File file) {
//                        return getBitmapFromFile(file);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        imageCollectorView.addImage(bitmap);
//                    }
//                });
        //=======
        // rx lambda java
//        Observable.from(folders)
//                .flatMap((Func1) (folder) -> Observable.from(file.listFiles()))
//                .filter((Func1) (file) -> file.getName().endsWith(".png"))
//                .map((Func1) (file) -> getBitmapFromFile(file))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((bitmap) -> { imageCollectorView.addImage(bitmap)});

//(1)
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.e(LOG_TAG, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.e(LOG_TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(LOG_TAG, "Error!");
            }
        };
//(2)
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
//(1)-observer-subscribe (1)
        Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();


            }
        });

//(2)-observer-subscribe (2)
        Observable observable = Observable.just("Hello", "Hi", "Aloha");

        observable.subscribe(observer);
// 或者：
        observable.subscribe(subscriber);


//(1）(2)不完整订阅

        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.e(LOG_TAG, s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.e(LOG_TAG, "completed");
            }
        };

// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);


// （3）事件变换
//        Observable.just("images/logo.png") // 输入类型 String
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String filePath) { // 参数类型 String
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
//                    }
//                })
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
//                        showBitmap(bitmap);
//                    }
//                });
    }


    public void rxJavaDemo1() {
        String[] names = new String[]{"Hello", "Hi", "Aloha"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.e(LOG_TAG, ">>>>>>>>>>>>>>:" + name);
                    }
                });
    }


    public void rxJavaDemo2() {
        int drawableRes = R.drawable.ic_launcher;
        ImageView imageView = (ImageView) findViewById(R.id.rxjava_image);

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaDemoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });


        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.e(LOG_TAG, "number:" + number);
                    }
                });
    }
}
