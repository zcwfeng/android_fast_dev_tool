package com.zcwfeng.fastdev.ui.fragment.custom_views;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class AndroidDrawableFragment extends BaseFragment {

    private View rootView;
    private ImageView mImg;
    private Button mBtn;

    public static AndroidDrawableFragment newInstance() {

        Bundle args = new Bundle();

        AndroidDrawableFragment fragment = new AndroidDrawableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_gif_androiddrawable_layout, container, false);
        mImg = (ImageView) rootView.findViewById(R.id.img_androiddrawable);
        mBtn = (Button) rootView.findViewById(R.id.start_gif);
        mBtn.setOnClickListener(v -> {
            mImg.setImageDrawable(getResources().getDrawable(R.drawable.gift_trophy1));
            AnimationDrawable animationDrawable = (AnimationDrawable) mImg.getDrawable();
            animationDrawable.start();
            mImg.postDelayed(() -> {
                mImg.clearAnimation();
                mImg.destroyDrawingCache();
            },12 * 100 );


            //2
            mImg.postDelayed(() -> {
                mImg.setImageDrawable(getResources().getDrawable(R.drawable.gift_trophy2));
                AnimationDrawable animationDrawable2 = (AnimationDrawable) mImg.getDrawable();
                animationDrawable2.start();
            },12 * 100 );


            mImg.postDelayed(() -> {
                mImg.clearAnimation();
                mImg.destroyDrawingCache();
            },24 * 100 );


            //3
            mImg.postDelayed(() -> {
                mImg.setImageDrawable(getResources().getDrawable(R.drawable.gift_trophy3));
                AnimationDrawable animationDrawable2 = (AnimationDrawable) mImg.getDrawable();
                animationDrawable2.start();
            },40 * 100 );


            mImg.postDelayed(() -> {
                mImg.clearAnimation();
                mImg.destroyDrawingCache();
            },40 * 100 );


            //4
            mImg.postDelayed(() -> {
                mImg.setImageDrawable(getResources().getDrawable(R.drawable.gift_trophy4));
                AnimationDrawable animationDrawable2 = (AnimationDrawable) mImg.getDrawable();
                animationDrawable2.start();
            },40 * 100 );


            mImg.postDelayed(() -> {
                mImg.clearAnimation();
                mImg.destroyDrawingCache();
            },56 * 100 );

        });
        return rootView;
    }
}
