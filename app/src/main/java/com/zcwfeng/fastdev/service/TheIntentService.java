package com.zcwfeng.fastdev.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by zcw on 2016/12/8.
 */

public class TheIntentService extends IntentService {
    public TheIntentService() {
        super("TheIntentService");
    }


    public TheIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //在这里执行耗时操作
    }
}
