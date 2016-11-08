package com.zcwfeng.fastdev.ndk;

public class NdkJniUtils {

    private static NdkJniUtils instance;

    public NdkJniUtils(){

    }

    public static NdkJniUtils getInstance(){
        if(instance == null){
            instance = new NdkJniUtils();
        }
        return instance;
    }


    static {
        System.loadLibrary("ZcwfengJniLibName");   //defaultConfig.ndk.moduleName

    }
    public native String getCLanguageString();

    public native void openServer();


    public native boolean Authenticate();


    public native String getStringFromC(String key,String value);

    public native int[] getIntArrayFromC(int[] array);


}