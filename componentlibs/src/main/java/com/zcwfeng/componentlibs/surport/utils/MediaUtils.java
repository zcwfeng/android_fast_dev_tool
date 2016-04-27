package com.zcwfeng.componentlibs.surport.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.view.View;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：媒体工具
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class MediaUtils {
    public static void generateCover4Video(String filePath, View v) throws Exception{
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        Bitmap bitmap = mmr.getFrameAtTime();
        v.setBackgroundDrawable(new BitmapDrawable(bitmap));
        mmr.release();
    }


    public static void playVideo(String url, Context context) throws Exception {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(url, new FileAsyncHttpResponseHandler(context) {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, File file) {
//                try {
//                    PlayAudioVideoActivity.launch(context,file.getPath());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });

    }



    /**
     * @param url 要下载的文件URL
     * @throws Exception
     */
    public static void downloadFile4Cover(String url,View mVideoLayout,Context context) throws Exception {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(url, new FileAsyncHttpResponseHandler(context) {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
//                LoggerUtil.e("bbj", "下载失败");
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, File file) {
//                try {
//                    AudioVideoTools.generateCover4Video(file.getPath(), mVideoLayout);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });

    }
}
