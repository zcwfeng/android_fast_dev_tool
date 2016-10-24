package com.zcwfeng.fastdev.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class ComponentFragment extends BaseFragment {

    private View rootView;

    public static ComponentFragment newInstance() {

        Bundle args = new Bundle();

        ComponentFragment fragment = new ComponentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, container, false);
        return rootView;
    }
}
