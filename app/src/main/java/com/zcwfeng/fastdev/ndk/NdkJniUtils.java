package com.zcwfeng.fastdev.ndk;

public class NdkJniUtils {

    static {
        System.loadLibrary("authenticate");
        System.loadLibrary("ZcwfengJniLibName");   //defaultConfig.ndk.moduleName

    }
    public native String getCLanguageString();

    public native void openServer();


    public native boolean Authenticate();
}