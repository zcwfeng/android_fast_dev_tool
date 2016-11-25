package com.zcwfeng.fastdev.ui.fragment.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by zcw on 2016/11/21.
 */

public class GreenDaoFragment extends BaseFragment {

    private View rootView;
    public static GreenDaoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GreenDaoFragment fragment = new GreenDaoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_greendao, container, false);
        initView();
        return rootView;
    }

    private void initView() {
    }
}
