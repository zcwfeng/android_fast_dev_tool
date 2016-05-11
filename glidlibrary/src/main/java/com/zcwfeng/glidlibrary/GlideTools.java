package com.zcwfeng.glidlibrary;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.net.URI;

/**
 * Created by David.zhang on 16/5/11.
 * Description：
 */
public class GlideTools {

    /**
     * 普通的加载网络图片的方法
     *
     * @param context
     * @param targetImageView
     * @param internetUrl
     */
    public static void loadImageByUrl(Context context, ImageView targetImageView, String internetUrl) {
        Glide
                .with(context)
                .load(internetUrl)
                .into(targetImageView);
    }

    /**
     * 普通的加载resource图片的方法
     *
     * @param context
     * @param imageViewResource
     * @param resourceId
     */
    public static void loadImageByResId(Context context, ImageView imageViewResource, int resourceId) {
        Glide
                .with(context)
                .load(resourceId)
                .into(imageViewResource);
    }


    /**
     * 普通的加载File图片的方法
     *
     * @param context
     * @param imageViewFile
     * @param file
     */
    public static void loadImageByResId(Context context, ImageView imageViewFile, File file) {
        Glide
                .with(context)
                .load(file)
                .into(imageViewFile);
    }


    /**
     * 从URI加载
     *
     * @param context
     * @param imageViewUri
     * @param uri
     */
    public static void loadImageByResId(Context context, ImageView imageViewUri, URI uri) {
        Glide
                .with(context)
                .load(uri)
                .into(imageViewUri);
    }


    //********************HELPER
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
    //*********************

    //*********************Test

    String internetUrl = "http://i.imgur.com/DvpvklR.png";
//    int resourceId = R.drawable.ic_launcher;

//这可能是任何 Uri。为了演示的目的我们只是用一个 launcher icon 去创建了一个 Uri
//Uri uri = resourceIdToUri(context, R.mipmap.future_studio_launcher);

    //这个文件可能不存在于你的设备中。然而你可以用任何文件路径，去指定一个图片路径。
    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Running.jpg");
    //**********************
}
