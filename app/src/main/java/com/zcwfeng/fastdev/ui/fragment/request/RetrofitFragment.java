package com.zcwfeng.fastdev.ui.fragment.request;

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
public class RetrofitFragment extends BaseFragment {

    private View rootView;

    public static RetrofitFragment newInstance() {

        Bundle args = new Bundle();

        RetrofitFragment fragment = new RetrofitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_retrofit, container, false);
        return rootView;
    }
}
