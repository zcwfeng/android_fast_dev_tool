package com.zcwfeng.componentlibs.ui.basic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.componentlibs.common.setting.SettingUtility;
import com.zcwfeng.componentlibs.surport.inject.InjectUtility;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class BaseActivity extends AppCompatActivity {

    private static BaseActivity runningActivity;

    private BaseActivityHelper mHelper;

    public static void launch(Context from,Class<?> clazz){
        Intent intent = new Intent(from,clazz);
        from.startActivity(intent);

    }


    public static BaseActivity getRunningActivity() {
        return runningActivity;
    }

    public static void setRunningActivity(BaseActivity activity) {
        runningActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtility.inject(this);

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

    @Override
    public void setContentView(View view) {
        super.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        InjectUtility.inject(this);

    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }


    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        InjectUtility.inject(this);
    }


}
