package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity_deprecated;
import com.zcwfeng.fastdev.R;

/**
 * Created by zcw on 16/6/30.
 */

public class ImageRefViewTestActivity extends BaseActivity_deprecated {


    public static void launch(Context from){
        Intent intent = new Intent(from,ImageRefViewTestActivity.class);
        from.startActivity(intent);
    }
    private ImageView mpathDataImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewtest);
        initviews();
    }

    private void initviews() {
        mpathDataImg = (ImageView) findViewById(R.id.pathData);
        mpathDataImg.setImageResource(R.drawable.pathdata_1);
    }



}

