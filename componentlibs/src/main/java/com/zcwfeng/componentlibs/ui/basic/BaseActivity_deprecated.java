package com.zcwfeng.componentlibs.ui.basic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zcwfeng.componentlibs.common.setting.SettingUtility;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class BaseActivity_deprecated extends AppCompatActivity {

    private static BaseActivity_deprecated runningActivity;

    private BaseActivityHelper mHelper;

    public static void launch(Context from,Class<?> clazz){
        Intent intent = new Intent(from,clazz);
        from.startActivity(intent);

    }


    public static BaseActivity_deprecated getRunningActivity() {
        return runningActivity;
    }

    public static void setRunningActivity(BaseActivity_deprecated activity) {
        runningActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mHelper == null) {
            try {
                if (SettingUtility.getStringSetting("activity_helper") != null) {
                    mHelper = (BaseActivityHelper) Class.forName(SettingUtility.getStringSetting("activity_helper")).newInstance();
                    mHelper.bindActivity(this);
                }
            } catch (Exception e) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRunningActivity(this);
    }


    protected int configTheme() {
        if (mHelper != null) {
            int theme = mHelper.configTheme();
            if (theme > 0)
                return theme;
        }

        return -1;
    }



}
