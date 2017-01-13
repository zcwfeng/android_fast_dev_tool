package com.zcwfeng.fastdev.ui.fragment.skill;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class AppIntentFragment extends BaseFragment implements View.OnClickListener{
    public static final String VIDEO_ID = "VIDEO_ID";
    public static final String MULTI_SETA = "MULTI_SETA";
    public static final String SCREEN_ORIENTAION = "SCREEN_ORIENTAION";
    private View rootView;
    public static AppIntentFragment newInstance() {
        Bundle args = new Bundle();
        AppIntentFragment fragment = new AppIntentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_app_intent, container, false);
        rootView.findViewById(R.id.intent_startv_main).setOnClickListener(this);
        rootView.findViewById(R.id.intent_startv_play).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.intent_startv_play:
                startPlay();
                break;
            case R.id.intent_startv_main:
                startMain();
                break;
        }
    }

    private void startPlay(){
        ComponentName mComp;
        Intent mIntent;
        mIntent = new Intent();

        mIntent.putExtra(VIDEO_ID, 30000836);
        mIntent.putExtra(MULTI_SETA, false);
        mIntent.putExtra(SCREEN_ORIENTAION, "VERTICIAL");
        mIntent.putExtra("ACTIVITY_ACTION","android.intent.action.LivePlayerHorizontalActivity");
        mComp = new ComponentName("com.yinyuetai.live","com.yinyuetai.live.ui.activity.start.SplashActivity");//注意AcitivityName(目标应用程序)要完整的，带包名的PackageName的
        mIntent.setComponent(mComp);
        mIntent.setAction("android.intent.action.MAIN");

        startActivity(mIntent);
    }

    private void startMain() {
        Intent mIntent = new Intent();
        mIntent.setAction("android.intent.action.MAIN");
        ComponentName mComp = new ComponentName("com.yinyuetai.live","com.yinyuetai.live.ui.activity.start.SplashActivity");//注意AcitivityName(目标应用程序)要完整的，带包名的PackageName的
        mIntent.setComponent(mComp);
        startActivity(mIntent);
    }
}
