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

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;
import static com.zcwfeng.fastdev.basic.Config.MULTI_SETA;
import static com.zcwfeng.fastdev.basic.Config.SCREEN_ORIENTAION;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class AppIntentFragment extends BaseFragment implements View.OnClickListener{
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
        ComponentName mComp;
        Intent mIntent;
        switch (v.getId()) {
            case R.id.intent_startv_play:
                 mIntent = new Intent();
                mIntent.putExtra(VIDEO_ID, 30000168);
                mIntent.putExtra(MULTI_SETA, true);
                mIntent.putExtra(SCREEN_ORIENTAION, "HORIZONTAL");
                mIntent.setAction("android.intent.action.LivePlayerHorizontalActivity");
                mComp = new ComponentName("com.yinyuetai.live","com.yinyuetai.live.ui.activity.video.LivePlayerHorizontalActivity");//注意AcitivityName(目标应用程序)要完整的，带包名的PackageName的
                mIntent.setComponent(mComp);
                startActivity(mIntent);
                break;
            case R.id.intent_startv_main:
                mIntent = new Intent();
                mIntent.setAction("android.intent.action.MAIN");
                mComp = new ComponentName("com.yinyuetai.live","com.yinyuetai.live.ui.activity.start.SplashActivity");//注意AcitivityName(目标应用程序)要完整的，带包名的PackageName的
                mIntent.setComponent(mComp);
                startActivity(mIntent);
                break;
        }
    }
}
