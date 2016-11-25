package com.zcwfeng.fastdev.ui.fragment.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.demorealm.activity.RealmDemoMainActivity;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by zcw on 2016/11/21.
 */

public class RealmFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    public static RealmFragment newInstance() {

        Bundle args = new Bundle();

        RealmFragment fragment = new RealmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_realm, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        rootView.findViewById(R.id.realm_demo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.realm_demo:
                RealmDemoMainActivity.launch(getActivity(),RealmDemoMainActivity.class);
                break;
        }
    }

}

