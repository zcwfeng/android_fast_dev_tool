package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.widget.MyScrollView;

/**
 * Created by David.zhang on 16/5/19.
 * Description：
 */
public class DampingScrollActivity extends BaseActivity implements MyScrollView.OnHeaderRefreshListener {
    private ImageView mBackgroundImageView = null;
    private MyScrollView mScrollView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damping_scroll);
        mBackgroundImageView = (ImageView) findViewById(R.id.personal_background_image);
        mScrollView = (MyScrollView) findViewById(R.id.personal_scrollView);
        mScrollView.setImageView(mBackgroundImageView);
        mScrollView.setOnHeaderRefreshListener(this);


    }

    @Override
    public void onHeaderRefresh(MyScrollView view) {
        Toast.makeText(getApplicationContext(), "刷新", Toast.LENGTH_LONG).show();
    }
}
