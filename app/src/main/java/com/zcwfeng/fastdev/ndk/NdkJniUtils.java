package com.zcwfeng.fastdev.ndk;

import com.crashlytics.android.Crashlytics;
import com.getkeepsafe.relinker.ReLinker;
import com.zcwfeng.fastdev.basic.MyApplication;

import io.fabric.sdk.android.Fabric;

public class NdkJniUtils {

    private static NdkJniUtils instance;

    public NdkJniUtils() {

    }

    public static NdkJniUtils getInstance() {
        if (instance == null) {
            instance = new NdkJniUtils();
        }
        return instance;
    }


    static {
        try {
            System.loadLibrary("ZcwfengJniLibName");   //defaultConfig.ndk.moduleName
        } catch (UnsatisfiedLinkError e) {
            // 部分机型加载so出现问题,尝试使用ReLinker库加载
            ReLinker.loadLibrary(MyApplication.getInstance(), "ZcwfengJniLibName");
            // 同时上传日志
            if (Fabric.isInitialized()) {
                Crashlytics.getInstance().logException(e);
            }
        }

    }

    public native String getCLanguageString();

    public native void openServer();


    public native boolean Authenticate();


    public native String getStringFromC(String key, String value);

    public native int[] getIntArrayFromC(int[] array);


}