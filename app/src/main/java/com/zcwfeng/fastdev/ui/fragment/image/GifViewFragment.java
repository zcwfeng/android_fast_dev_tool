package com.zcwfeng.fastdev.ui.fragment.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cunoraz.gifview.library.GifView;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class GifViewFragment extends BaseFragment {

    private View rootView;
    private GifView gifView1;
    public static GifViewFragment newInstance() {

        Bundle args = new Bundle();

        GifViewFragment fragment = new GifViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_gif_view, container, false);

//        gifView1 = (GifView) rootView.findViewById(R.id.gif1);
//        gifView1.setVisibility(View.VISIBLE);
//        gifView1.play();
//        gifView1.pause();
//        gifView1.setGifResource(R.mipmap.gif5);
//        gifView1.getGifResource();
//        gifView1.setMovieTime(time);
//        gifView1.getMovie();
        return rootView;
    }

}
