package com.zcwfeng.fastdev.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zcwfeng.fastdev.basic.AppSettings;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class ProjectUtils {

    public static void setStatusBar(Activity activity) {
//        if (true) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );//| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Utils.resolveColor(activity, R.attr.theme_statusbar_color, Color.BLUE));
            window.setStatusBarColor(Color.parseColor("#20000000"));
            window.setNavigationBarColor(activity.getResources().getColor(ThemeUtils.themeColorArr[AppSettings.getThemeColor()][1]));
//            window.setNavigationBarColor(Utils.resolveColor(activity, R.attr.theme_color, Color.BLUE));

//            Window window = activity.getWindow();
//            window.requestFeature(Window.FEATURE_NO_TITLE);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Utils.resolveColor(activity, R.attr.theme_statusbar_color, Color.TRANSPARENT));
        }
    }


    public static void openPackage(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
        }

    }

    public static void openUrl(Context context, String url) {
        if (Patterns.WEB_URL.matcher(url).matches()) {
            Uri content_url = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, content_url);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
            }
        }
    }


}


