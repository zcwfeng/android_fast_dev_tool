package com.zcwfeng.fastdev.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zcwfeng.componentlibs.surport.inject.ContentView;
import com.zcwfeng.componentlibs.surport.inject.ViewInject;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.utils.ProjectUtils;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
@ContentView(value = R.layout.activity_user_profile)
public class UserProfileActivity extends BaseActivity {

    public static void launch(Activity from) {
        Intent intent = new Intent(from, UserProfileActivity.class);
        from.startActivity(intent);
    }

    @ViewInject(id = R.id.layContent)
    View layoutContent;
    @ViewInject(id = R.id.layoutLoadFailed)
    View layoutLoadFailed;
    @ViewInject(id = R.id.txtLoadFailed)
    TextView txtLoadFailed;
    @ViewInject(id = R.id.layoutReload, click = "reload")
    View layoutReload;
    @ViewInject(id = R.id.layToolbar)
    ViewGroup layToolbar;

    @ViewInject(id = R.id.viewToolbar)
    View viewToolbar;

//    private boolean searchFailed = false;
//
//    private String screenName;
//
//    private WeiBoUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProjectUtils.setStatusBar(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        layToolbar = (ViewGroup) findViewById(R.id.layToolbar);
//        layoutContent = findViewById(R.id.layoutContent);
//        layoutLoadFailed  = findViewById(R.id.layoutLoadFailed);
//        layoutReload = findViewById(R.id.layoutReload);
//        viewToolbar = findViewById(R.id.viewToolbar);
//        txtLoadFailed = (TextView) findViewById(R.id.txtLoadFailed);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            layToolbar.setPadding(layToolbar.getPaddingLeft(),
//                    layToolbar.getPaddingTop() + SystemBarUtils.getStatusBarHeight(this),
//                    layToolbar.getPaddingRight(),
//                    layToolbar.getPaddingBottom());
//
//            viewToolbar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                    getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material) + SystemBarUtils.getStatusBarHeight(this)));
//        }

//        if (savedInstanceState == null && getIntent() != null) {
//        }
//        else {
//            if (savedInstanceState != null);
//        }

//        if (mUser != null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.layContent, UserProfilePagerFragment.newInstance(mUser), FragmentContainerActivity.FRAGMENT_TAG)
//                    .commit();
//            return;
//        }

//        searchFailed = savedInstanceState == null ? false : savedInstanceState.getBoolean("searchFailed");
//        screenName = savedInstanceState == null ? null : savedInstanceState.getString("screenName");

        txtLoadFailed.setText(R.string.error_pic_load_faild);

        if (savedInstanceState == null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_user_profile);

            Uri data = getIntent().getData();
            if (data != null) {
                String d = data.toString();
                int index = d.lastIndexOf("/");
                String userName = d.substring(index + 1);
                if (userName.indexOf("@") == 0)
                    userName = userName.substring(1);

//                screenName = userName;
//
//                reload(null);
            } else {
//                finish();
                return;
            }
        }

//        if (searchFailed) {
//            reload(null);
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



}
