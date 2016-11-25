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

public class OriginFragment extends BaseFragment {

    private View rootView;


    public static OriginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        OriginFragment fragment = new OriginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_origin, container, false);
        initView();
        return rootView;
    }

    private void initView() {
    }

}
