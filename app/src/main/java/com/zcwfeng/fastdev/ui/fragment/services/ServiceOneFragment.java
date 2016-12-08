package com.zcwfeng.fastdev.ui.fragment.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.service.BackGroupService;
import com.zcwfeng.fastdev.service.ForegroundService;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class ServiceOneFragment extends BaseFragment {

    private View rootView;
    private Button mCloseButton;
    private Button mOpenButton;


    public ServiceOneFragment() {
    }

    public static ServiceOneFragment newInstance() {

        Bundle args = new Bundle();

        ServiceOneFragment fragment = new ServiceOneFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_service_one, container, false);
        Intent intent = new Intent(getActivity(), BackGroupService.class);
        mCloseButton = (Button) rootView.findViewById(R.id.close_service);
        mOpenButton = (Button) rootView.findViewById(R.id.start_service);

        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().bindService(intent,conn, BIND_AUTO_CREATE);
                mOpenButton.setClickable(false);

            }
        });
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().unbindService(conn);
                mCloseButton.setClickable(false);
            }
        });


        //启动前台服务
        getActivity().startService(new Intent(getActivity(), ForegroundService.class));
        return rootView;
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BackGroupService.MyBinder myBinder = (BackGroupService.MyBinder) service;
            myBinder.showToast();
            myBinder.showList();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
