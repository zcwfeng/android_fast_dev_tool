package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ndk.NdkJniUtils;

/**
 * Created by David.zhang on 16/4/26.
 * Description：
 */
public class NDKDemoActivity extends BaseActivity {

    static {
        System.loadLibrary("ZcwfengJniLibName");   //defaultConfig.ndk.moduleName
    }

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_demo);
        mTextView = (TextView) this.findViewById(R.id.test);
        NdkJniUtils jni = new NdkJniUtils();
        mTextView.setText(jni.getCLanguageString());
    }

    public static void launch(Context from) {
        Intent intent = new Intent(from, NDKDemoActivity.class);
        from.startActivity(intent);

    }
}
