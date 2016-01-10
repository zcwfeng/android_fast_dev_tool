package com.zcwfeng.componentlibs.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtility.inject(this);
    }


//    @Override
//    public void setContentView(View view) {
//        super.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        InjectUtility.inject(this);
//
//    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//        setContentView(View.inflate(this, layoutResID, null));
//    }
//
//
//
//    @Override
//    public void setContentView(View view, ViewGroup.LayoutParams params) {
//        super.setContentView(view, params);
//        InjectUtility.inject(this);
//    }


}
