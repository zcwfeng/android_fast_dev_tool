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


package com.zcwfeng.fastdev.secure.skb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cryptanium.skb.SecureData;
import com.cryptanium.skb.provider.SkbProvider;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.secure.skb.rsa.SKBRsa;
import com.zcwfeng.fastdev.secure.test.TestUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 测试加解密
 */
public class ExamplesRSA_DESActivity extends BaseActivity


{

    public static void launch(Context from){
        Intent intent = new Intent(from,ExamplesRSA_DESActivity.class);
        from.startActivity(intent);
    }
    public static final String TAG = "SkbExamples";

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
    
    AndroidLogger logger;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        boolean isSKB = false;
        if(isSKB) {
            testSkb();
        } else {
            try {
                TestUtils.test(ExamplesRSA_DESActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public String getFromRaw(int id){
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().openRawResource(id));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public void testSkb(){
        logger = new AndroidLogger();

        try
        {
            Security.addProvider(new SkbProvider());
        }
        catch (Exception e)
        {
            logger.logError("Failed to add SkbProvider");
            System.exit(1);
        }

//        try
//        {
//            Security.addProvider(new SkbHighSpeedAesProvider());
//        }
//        catch (Exception e)
//        {
//            logger.logError("Failed to add SkbHighSpeedAesProvider");
//            System.exit(1);
//        }


//        AESExample.run(logger);
//        logger.logInfo("Examples done");


        SKBRsa.init(getFromRaw(R.raw.exported),getFromRaw(R.raw.encrypted));
        try {
            SKBRsa.descSKB();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
//        finish();
    }
}

