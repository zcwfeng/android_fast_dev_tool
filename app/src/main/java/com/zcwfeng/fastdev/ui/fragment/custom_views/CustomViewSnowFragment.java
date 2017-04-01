package com.zcwfeng.fastdev.ui.fragment.custom_views;

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
public class CustomViewSnowFragment extends BaseFragment {

    private View rootView;

    public static CustomViewSnowFragment newInstance() {

        Bundle args = new Bundle();

        CustomViewSnowFragment fragment = new CustomViewSnowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_customview_snow_layout, container, false);
        return rootView;
    }
}
