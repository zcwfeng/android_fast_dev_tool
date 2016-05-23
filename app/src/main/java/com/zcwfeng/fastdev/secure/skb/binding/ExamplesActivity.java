/*****************************************************************
|
|   Cryptanium Secure Key Box
|
|   $Id: ExamplesActivity.java 7790 2016-01-05 09:52:35Z kstraupe $
|
|   This software is provided to you pursuant to your Software
|   license agreement (SLA) with whiteCryption Corporation
|   ("whiteCryption") and Intertrust Technologies Corporation
|   ("Intertrust"). This software may be used only in accordance
|   with the terms of this agreement.
|
|   Copyright (c) 2000-2016, whiteCryption Corporation. All rights reserved.
|   Copyright (c) 2004-2016, Intertrust Technologies Corporation. All rights reserved.
|
****************************************************************/


package com.zcwfeng.fastdev.secure.skb.binding;

import android.os.Bundle;
import android.util.Log;

import com.cryptanium.skb.SecureData;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;

public class ExamplesActivity extends BaseActivity
{
    
    public static final String TAG = "SkbJavaExamples";
    
    public class AndroidLogger implements Logger
    {
        AndroidLogger() {}
        
        public void logError(String message) {
            Log.e(TAG, message);
        }
        
        public void logDebug(String message) {
            Log.d(TAG, message);
        }
        
        public void logInfo(String message) {
            Log.i(TAG, message);
        }
        
        public void dumpSecureDataInfo(SecureData data)
        {
            SecureData.DataInfo info = data.getInfo();
            
            Log.d(TAG, "SecureData Type: " + info.type.name());
            Log.d(TAG, "SecureData Size: " + info.size);
            
        }
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        AndroidLogger logger = new AndroidLogger();
        super.onCreate(savedInstanceState);
        ImportExport.run(logger);
        AesCipher.run(logger);
        WrapUnwrap.run(logger);
        logger.logInfo("Examples done");
        finish();
    }
}

