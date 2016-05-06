package com.zcwfeng.componentlibs.surport.utils;

import android.util.DisplayMetrics;

import com.zcwfeng.componentlibs.BaseApplication;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：UI工具
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class UIUtils {
    private static int screen_width;
    private static int screen_height;

    public static void initScreenWH() {
        DisplayMetrics metrics = getDisplayMetrics();
        screen_width = metrics.widthPixels;
        screen_height = metrics.heightPixels;
    }

    public static int getScreenWidth() {
        return screen_width;
    }

    public static int getScreenHeight() {
        return screen_height;
    }

    /**
     * 获取显示参数
     */
    static DisplayMetrics getDisplayMetrics() {
        return BaseApplication.getInstance().getResources().getDisplayMetrics();
    }
}
