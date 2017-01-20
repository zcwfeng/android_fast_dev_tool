package com.zcwfeng.fastdev.ui.fragment.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.activity.TempActivity;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class WeexDownloadFragment extends BaseFragment implements View.OnClickListener, IWXRenderListener {
    private ViewGroup rootView;

    WXSDKInstance mWXSDKInstance;


    public static WeexDownloadFragment newInstance() {
        Bundle args = new Bundle();
        WeexDownloadFragment fragment = new WeexDownloadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWXSDKInstance = new WXSDKInstance(getActivity());
        mWXSDKInstance.registerRenderListener(this);
        mWXSDKInstance.render("WXSample", WXFileUtils.loadFileContent("hello.js", getActivity()), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_weex_download, container, false);
//        rootView.findViewById(R.id.weex_download).setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.weex_download:
                TempActivity.launch(getActivity(),TempActivity.class);
                break;
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        rootView.addView(view);

    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
