package com.zcwfeng.fastdev.ui.fragment.skill;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
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

    private ComponentName mDefault;
    private ComponentName mDouble11;
    private ComponentName mDouble12;
    private PackageManager mPm;


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
        rootView.findViewById(R.id.icon_change_s11).setOnClickListener(this);
        rootView.findViewById(R.id.icon_change_s12).setOnClickListener(this);

        mDefault = new ComponentName(getActivity().getBaseContext(),"com.zcwfeng.fastdev.ui.activity.SplashActivity");
        mDouble11 = new ComponentName(
                getActivity().getBaseContext(),
                "com.zcwfeng.fastdev.ui.activity.Splash_s11");
        mDouble12 = new ComponentName(
                getActivity().getBaseContext(),
                "com.zcwfeng.fastdev.ui.activity.Splash_s12");
        mPm = getActivity().getApplicationContext().getPackageManager();
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
            case R.id.icon_change_s11:
                changeIcon11();
                break;
            case R.id.icon_change_s12:
                changeIcon12();
                break;
        }
    }

    private void startPlay(){
        ComponentName mComp;
        Intent mIntent;
        mIntent = new Intent();

        mIntent.putExtra(VIDEO_ID, 30000836);
        mIntent.putExtra(MULTI_SETA, false);
        mIntent.putExtra(SCREEN_ORIENTAION, "VERTICAL");
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

    public void changeIcon11() {
        disableComponent(mDefault);
        disableComponent(mDouble12);
        enableComponent(mDouble11);
    }

    public void changeIcon12() {
        disableComponent(mDefault);
        disableComponent(mDouble11);
        enableComponent(mDouble12);
    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
