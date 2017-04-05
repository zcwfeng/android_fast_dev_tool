package com.zcwfeng.fastdev.ui.fragment.services;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
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
public class ServiceTwoFragment extends BaseFragment {

    private View rootView;

    public static ServiceTwoFragment newInstance() {

        Bundle args = new Bundle();

        ServiceTwoFragment fragment = new ServiceTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_service_two, container, false);
        return rootView;
    }




    public void isWifi() {
        WifiManager wm = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        boolean enabled = wm.isWifiEnabled();
    }

    public void getMaxAudio() {
       AudioManager am = (AudioManager) getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);

        int current = am.getStreamMaxVolume(AudioManager.STREAM_RING);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void isConnect() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        boolean isAvailable = info.isAvailable();
    }
}
