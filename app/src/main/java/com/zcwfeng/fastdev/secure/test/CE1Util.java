package com.zcwfeng.fastdev.secure.test;


import android.util.Log;

/**
 * Time: 2016/5/22.
 */
public class CE1Util {


    public static void main(String[] args){
        String sourceCode = "12-101-VOD";
        System.out.println(sourceCode);

        int channelId = 12;
        long videoId = 101;
        String videoType = "VOD";

        byte[] channelIdBytes = BytesUtil.toLH(channelId);
        System.out.println("channelIdBytes : " + getStringFromBytes(channelIdBytes));

        byte[] videoIdBytes = BytesUtil.toLH(videoId);
        System.out.println("videoIdBytes : " + getStringFromBytes(videoIdBytes));

        byte[] videoTypeBytes = videoType.getBytes();
        System.out.println("videoTypeBytes : " + getStringFromBytes(videoTypeBytes));

        byte[] initArr = BytesUtil.combineBytes(channelIdBytes, videoIdBytes);
        System.out.println("initArr combine channelId and videoId : " + getStringFromBytes(initArr));

        initArr = BytesUtil.combineBytes(initArr, videoTypeBytes);
        System.out.println("initArr combine videoType : " + getStringFromBytes(initArr));

        String md5 = md5Value(initArr);
        System.out.println("md5 result : " + md5);
    }

    public byte[] combineParam(String[] params){
        byte[] bytes = params[0].getBytes();

        Log.d("zcw",0 + " : " + params[0]);
        for (int i = 1; i < params.length; i++) {
            Log.d("zcw",i + " : " + params[i]);
            bytes = BytesUtil.combineBytes(bytes, params[i].getBytes());
        }

        return bytes;
    }

    /**
     * 客户端md5逻辑实现
     * @param bytes
     * @return
     */
    public static String md5Value(byte[] bytes){
        Log.d("zcw","原始byte数组格式化输出 : " + getStringFromBytes(bytes));
        System.out.println("原始byte数组格式化输出 : " + getStringFromBytes(bytes));

        bytes = BytesUtil.sortLH(bytes);//1 低到高
        Log.d("zcw","第一次排序-低到高-格式化输出 : " + getStringFromBytes(bytes));
        System.out.println("第一次排序-低到高-格式化输出 : " + getStringFromBytes(bytes));

        bytes = MD5Util.MD5Encrypt(bytes);//1 MD5
        Log.d("zcw","第一次MD5-格式化输出 : " + getStringFromBytes(bytes));
        System.out.println("第一次MD5-格式化输出 : " + getStringFromBytes(bytes));

        bytes = BytesUtil.sortHL(bytes);//2 高到低
        Log.d("zcw","第二次排序-高到低-格式化输出 : " + getStringFromBytes(bytes));
        System.out.println("第二次排序-高到低-格式化输出 : " + getStringFromBytes(bytes));

        bytes = MD5Util.MD5Encrypt(bytes);//2 MD5
        Log.d("zcw","第二次MD5-格式化输出 : " + getStringFromBytes(bytes));
        System.out.println("第二次MD5-格式化输出 : " + getStringFromBytes(bytes));

        bytes = BytesUtil.sortLH(bytes);//3 低到高
        Log.d("zcw","第三次排序-低到高-格式化输出 : " + getStringFromBytes(bytes));
        System.out.println("第三次排序-低到高-格式化输出 : " + getStringFromBytes(bytes));

        return MD5Util.md5(bytes);
    }


    public static String getStringFromBytes(byte[] bytes){
        StringBuffer buf = new StringBuffer("");
        int i;

        for (int offset = 0; offset < bytes.length; offset++) {
            i = bytes[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

}

