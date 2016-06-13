package com.zcwfeng.componentlibs.surport.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import com.zcwfeng.componentlibs.BaseApplication;

import java.io.File;
import java.math.BigDecimal;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：设备本地获取信息等工具
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class DeviceUtils {
    public final static String tag = DeviceUtils.class.getSimpleName();


    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    // TODO: 16/5/27 整理到FormatterUtils里面

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    /**
     * 根据路径获取内存状态
     *
     * @param path
     * @return
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private static String getMemoryInfo(File path, Context context) {
        // 获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();   // 获得一个扇区的大小
        long totalBlocks = stat.getBlockCount();    // 获得扇区的总数
        long availableBlocks = stat.getAvailableBlocks();   // 获得可用的扇区数量
        // 总空间
        String totalMemory = Formatter.formatFileSize(context, totalBlocks * blockSize);
        // 可用空间
        String availableMemory = Formatter.formatFileSize(context, availableBlocks * blockSize);
        return "总空间: " + totalMemory + "\n可用空间: " + availableMemory;
    }


    private void displayBriefMemory() {
        final ActivityManager activityManager = (ActivityManager) BaseApplication.getInstance().getSystemService(Activity.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.i(tag, "系统剩余内存:" + (info.availMem >> 10) + "k");
        Log.i(tag, "系统是否处于低内存运行：" + info.lowMemory);
        Log.i(tag, "当系统剩余内存低于" + info.threshold + "时就看成低内存运行");

    }
}
