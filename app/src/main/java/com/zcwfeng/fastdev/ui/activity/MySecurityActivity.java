package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcwfeng.fastdev.R;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zcw on 2017/3/17.
 */

public class MySecurityActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MySecurityActivity.class);
        context.startActivity(intent);
    }

  /*
  1.
  慕课网：http://www.imooc.com/course/ajaxmediainfo/?mid=12141&mode=flash
  协议破解：mid 号码12141，解析的json格式如下


  */



    /*
    {
        "result":0,
            "data":{
                "result":{
                    "mid":12141,
                    "mpath":[
                    "http:\/\/v2.mukewang.com\/9945a8fb-7d3d-4695-bbc1-3796d75c4ea8\/L.mp4?auth_key=1490343513-0-0-289e9a31e6c64bc8f9d2823137dad646",
                            "http:\/\/v2.mukewang.com\/9945a8fb-7d3d-4695-bbc1-3796d75c4ea8\/M.mp4?auth_key=1490343513-0-0-e54b753a81133b1cd82f9a8095ca1ba0",
                            "http:\/\/v2.mukewang.com\/9945a8fb-7d3d-4695-bbc1-3796d75c4ea8\/H.mp4?auth_key=1490343513-0-0-81be0627c6e2baf014cd251b60e35218"],
                    "cpid":"3261",
                    "name":"jQuery\u8bfe\u7a0b\u5185\u5bb9\u548c\u76ee\u6807",
                    "time":0,
                    "practise":[]
                }
        },
        "msg":"\u6210\u529f"
    }
    */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_layout);
        setToolbar(null, "快速开发库");

        try {
            java.lang.Process process = java.lang.Runtime.getRuntime().exec("su");
            OutputStream os = process.getOutputStream();
            os.write("ls /system/app".getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
