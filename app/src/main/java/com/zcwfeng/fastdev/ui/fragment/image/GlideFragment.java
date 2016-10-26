package com.zcwfeng.fastdev.ui.fragment.image;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by David.zhang on 2016/10/24.
 * Description：
 */
public class GlideFragment extends BaseFragment {

    private View rootView;
    private ImageView mImageView;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private ImageView mImageViewVideo;
    private ImageView mImageViewFile;

    public static GlideFragment newInstance() {

        Bundle args = new Bundle();

        GlideFragment fragment = new GlideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_glid, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        mImageView = (ImageView) rootView.findViewById(R.id.list_item_bg);
        mImageView2 = (ImageView) rootView.findViewById(R.id.list_item_bg2);
        mImageViewFile = (ImageView) rootView.findViewById(R.id.list_item_file);
        mImageView3 = (ImageView) rootView.findViewById(R.id.list_item_bg3);
        mImageView4 = (ImageView) rootView.findViewById(R.id.list_item_bg_4);
        mImageView5 = (ImageView) rootView.findViewById(R.id.list_item_bg_5);
        mImageViewVideo = (ImageView) rootView.findViewById(R.id.list_item_bg_video);


        Glide.with(getContext().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_url))
                .placeholder(getResources().getDrawable(R.mipmap.demo_mn))
                .error(getResources().getDrawable(R.drawable.biz_video_list_play_icon_big))
                .dontAnimate()
                .priority(Priority.LOW)
                .into(mImageView);
        Glide.with(getContext().getApplicationContext()).load(R.drawable.bg).into(mImageView2);


        File file = new File(Environment.getExternalStorageDirectory(), "duola.jpeg");
//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "duola.jpeg");
//        File file = new File(getAssetsCacheFile(getActivity()));
        Glide.with(getActivity().getApplicationContext()).load(file)
                .placeholder(getResources().getDrawable(R.mipmap.demo_mn))
                .error(getResources().getDrawable(R.drawable.biz_video_list_play_icon_big))
                .into(mImageViewFile);


        // this could be any Uri. for demonstration purposes we're just creating an Uri pointing to a launcher icon
//        Uri uri = resourceIdToUri(getActivity(), R.mipmap.ic_launcher);
        Uri uri = Uri.parse(getResources().getString(R.string.glide_single_img_uri_url));
//      override 和crossFade放一块,最好加上dontAnimate
        Glide.with(getActivity().getApplicationContext()).load(uri)
//                .crossFade(2000)
                .dontAnimate()
                .override(600, 200)
                .centerCrop()
//                .fitCenter()
                .priority(Priority.HIGH)
                .into(mImageView3);


        // gif
        Glide.with(getActivity().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_gif_url))
//                .asGif()
                .priority(Priority.LOW)
                .into(mImageView4);

        // gif as bitmap
        Glide.with(getActivity().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_gif_url))
                .asBitmap()
                .priority(Priority.LOW)
                .into(mImageView5);

        // video cache and diskStoryCache
        Glide.with(getActivity().getApplicationContext()).load(Uri.fromFile(new File(getResources().getString(R.string.demo_video_path))))
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .signature()
                .skipMemoryCache(true)
                .priority(Priority.LOW)
                .into(mImageViewVideo);
    }






//    public class IntegerVersionSignature implements Key {
//        private int currentVersion;
//
//        public IntegerVersionSignature(int currentVersion) {
//            this.currentVersion = currentVersion;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o instanceof IntegerVersionSignature) {
//                IntegerVersionSignature other = (IntegerVersionSignature) o;
//                return currentVersion = other.currentVersion;
//            }
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            return currentVersion;
//        }
//
//        @Override
//        public void updateDiskCacheKey(MessageDigest md) {
//            messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(signature).array());
//        }
//    }

    @NonNull
    private String getAssetsCacheFile(Context mContext) {
        File cacheFile = new File(Environment.getExternalStorageDirectory(), "temp");

        try {
            InputStream e = mContext.getResources().openRawResource(R.raw.duola);

            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);

                try {
                    byte[] buf = new byte[1024];

                    int len;
                    while ((len = e.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                e.close();
            }
        } catch (IOException var16) {
            var16.printStackTrace();
        }

        return cacheFile.getAbsolutePath();
    }


    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
