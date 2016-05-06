package com.zcwfeng.httplibs;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.httplibs.builder.GetBuilder;
import com.zcwfeng.httplibs.builder.PostFileBuilder;
import com.zcwfeng.httplibs.builder.PostFormBuilder;
import com.zcwfeng.httplibs.builder.PostStringBuilder;
import com.zcwfeng.httplibs.callback.Callback;
import com.zcwfeng.httplibs.https.HttpsUtils;
import com.zcwfeng.httplibs.request.RequestCall;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils
{
    public static final long DEFAULT_MILLISECONDS = 10000;
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpUtils()
    {
        mOkHttpClient = new OkHttpClient();
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());

        if (false)
        {
            mOkHttpClient.setHostnameVerifier(new HostnameVerifier()
            {
                @Override
                public boolean verify(String hostname, SSLSession session)
                {
                    return true;
                }
            });
        }
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        String temp = BaseApplication.getInstance().getExternalCacheDir().getAbsolutePath() + File.separator + "cache_custom_okhttp/";
        File file = new File(temp);
        if(!file.exists()) file.mkdir();
        Cache cache = new Cache(file, cacheSize);
        if(cache != null)
        mOkHttpClient.setCache(cache);


    }

    public static OkHttpUtils getInstance()
    {
        if (mInstance == null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    public Handler getDelivery()
    {
        return mDelivery;
    }

    public OkHttpClient getOkHttpClient()
    {
        return mOkHttpClient;
    }


    public static GetBuilder get()
    {
        return new GetBuilder();
    }

    public static PostStringBuilder postString()
    {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile()
    {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post()
    {
        return new PostFormBuilder();
    }


    public void execute(final RequestCall requestCall, Callback callback)
    {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;

        requestCall.getCall().enqueue(new com.squareup.okhttp.Callback()
        {
            @Override
            public void onFailure(final Request request, final IOException e)
            {
                sendFailResultCallback(request, e, finalCallback);
            }

            @Override
            public void onResponse(final Response response)
            {
                if (response.code() >= 400 && response.code() <= 599)
                {
                    try
                    {
                        sendFailResultCallback(requestCall.getRequest(), new RuntimeException(response.body().string()), finalCallback);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return;
                }

                try
                {
                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (IOException e)
                {
                    sendFailResultCallback(response.request(), e, finalCallback);
                }

            }
        });
    }


    public void sendFailResultCallback(final Request request, final Exception e, final Callback callback)
    {
        if (callback == null) return;

        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onError(request, e);
                callback.onAfter();
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback)
    {
        if (callback == null) return;
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    public void cancelTag(Object tag)
    {
        mOkHttpClient.cancel(tag);
    }


    public void setCertificates(InputStream... certificates)
    {
        HttpsUtils.setCertificates(getOkHttpClient(), certificates, null, null);
    }


}

