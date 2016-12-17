package com.zcwfeng.fastdev.ui.fragment.image;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class FrescoFragment extends BaseFragment {

    private View rootView;

    public static FrescoFragment newInstance() {

        Bundle args = new Bundle();

        FrescoFragment fragment = new FrescoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fresco, container, false);

        initView();

        return rootView;
    }

    private void initView() {
        Uri uri = Uri.parse(getResources().getString(R.string.glide_single_img_debug_url));
        SimpleDraweeView draweeView = (SimpleDraweeView) rootView.findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
    }
}
