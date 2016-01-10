package com.zcwfeng.fastdev.BindingData;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zcwfeng.fastdev.BindingData.model.User;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.databinding.DatabindingLayoutBinding;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class MvvmDemoActivity extends AppCompatActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, MvvmDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabindingLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.databinding_layout);
        User user = new User("Test", "User");
        binding.setUser(user);
    }



}
