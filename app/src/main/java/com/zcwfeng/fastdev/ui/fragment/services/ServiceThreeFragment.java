package com.zcwfeng.fastdev.ui.fragment.services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.binder.AIDLTestActivity;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class ServiceThreeFragment extends BaseFragment {

    private View rootView;
    private Button mAIDLBtn;

    public static ServiceThreeFragment newInstance() {

        Bundle args = new Bundle();

        ServiceThreeFragment fragment = new ServiceThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_service_three, container, false);
        mAIDLBtn = (Button) rootView.findViewById(R.id.aidl_btn);
        mAIDLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AIDLTestActivity.launch(getActivity());
            }
        });
        return rootView;
    }

}
