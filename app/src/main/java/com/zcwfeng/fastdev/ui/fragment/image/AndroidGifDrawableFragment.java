package com.zcwfeng.fastdev.ui.fragment.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.image.gifdrawable.MainPagerAdapter;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class AndroidGifDrawableFragment extends BaseFragment {

    private View rootView;

    public static AndroidGifDrawableFragment newInstance() {

        Bundle args = new Bundle();

        AndroidGifDrawableFragment fragment = new AndroidGifDrawableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_android_gif_drawable, container, false);

        ((ViewPager)rootView.findViewById(R.id.main_pager)).setAdapter(new MainPagerAdapter(getActivity()));

        return rootView;
    }

}
