package com.zcwfeng.fastdev.ui.fragment.image;

import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.image.glide.BlurTransformation;
import com.zcwfeng.fastdev.ui.fragment.image.glide.FutureStudioView;
import com.zcwfeng.fastdev.ui.fragment.image.glide.GreyscaleTransformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Random;

import static com.bumptech.glide.Glide.with;


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
    private ImageView mImageView6;
    private ImageView mImageView7;
    private ImageView mImageView8;
    private ImageView mImageViewVideo;
    private ImageView mImageViewFile;
    private ImageView mImageViewDebuging;
    private ImageView mImageViewTransmation;
    private ImageView mImageViewTransmation2;
    private ImageView mImageViewTransmation3;
    private ImageView mImageViewAnim;

    private NotificationTarget notificationTarget;

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
        mImageView6 = (ImageView) rootView.findViewById(R.id.list_item_bg_6);
        mImageView7 = (ImageView) rootView.findViewById(R.id.list_item_bg_7);
        mImageView8 = (ImageView) rootView.findViewById(R.id.list_item_bg_8);
        mImageViewDebuging = (ImageView) rootView.findViewById(R.id.list_item_bg_debuging);
        mImageViewVideo = (ImageView) rootView.findViewById(R.id.list_item_bg_video);
        mImageViewTransmation = (ImageView) rootView.findViewById(R.id.list_item_bg_transformation1);
        mImageViewTransmation2 = (ImageView) rootView.findViewById(R.id.list_item_bg_transformation2);
        mImageViewTransmation3 = (ImageView) rootView.findViewById(R.id.list_item_bg_transformation3);
        mImageViewAnim = (ImageView) rootView.findViewById(R.id.list_item_bg_anim);


        with(getContext().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_url))
                .placeholder(getResources().getDrawable(R.mipmap.demo_mn))
                .error(getResources().getDrawable(R.drawable.biz_video_list_play_icon_big))
                .dontAnimate()
                .priority(Priority.LOW)
                .into(mImageView);
        with(getContext().getApplicationContext()).load(R.drawable.bg).into(mImageView2);

        // load file
        File file = new File(Environment.getExternalStorageDirectory(), "duola.jpeg");
        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "duola.jpeg");
        // File file = new File(getAssetsCacheFile(getActivity()));
        with(getActivity().getApplicationContext()).load(file)
                .placeholder(getResources().getDrawable(R.mipmap.demo_mn))
                .error(getResources().getDrawable(R.drawable.biz_video_list_play_icon_big))
                .into(mImageViewFile);


        // load uri
        // Uri uri = resourceIdToUri(getActivity(), R.mipmap.ic_launcher);
        Uri uri = Uri.parse(getResources().getString(R.string.glide_single_img_uri_url));
        // override 和crossFade放一块,最好加上dontAnimate
        with(getActivity().getApplicationContext()).load(uri)
//                .crossFade(2000)
                .dontAnimate()
                .override(600, 200)
                .centerCrop()
//                .fitCenter()
                .priority(Priority.HIGH)
                .into(mImageView3);


        // gif
        with(getActivity().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_gif_url))
//                .asGif()
                .priority(Priority.LOW)
                .into(mImageView4);

        // gif as bitmap
        with(getActivity().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_gif_url))
                .asBitmap()
                .priority(Priority.LOW)
                .into(mImageView5);

        // video cache and diskStoryCache
        with(getActivity().getApplicationContext()).load(Uri.fromFile(new File(getResources().getString(R.string.demo_video_path))))
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .signature()
                .skipMemoryCache(true)
                .priority(Priority.LOW)
                .into(mImageViewVideo);


        // thumbnail
        loadImageThumbnailRequest();

        // simple target
        loadImageSimpleTarget();
        loadImageSimpleTargetApplicationContext();
        loadImageViewTarget();

        // NotificationTarget
        notifyTarget();

        glideDebugingTest();

        // Apply a Single Transformation
        with(getActivity().getApplicationContext())
                .load(getResources().getString(R.string.glide_transmation_url_1))
                .transform(new BlurTransformation(getActivity()))
                //.bitmapTransform( new BlurTransformation( context ) ) // this would work too!
                .into(mImageViewTransmation);

        // Apply Multiple Transformations
        with(getActivity().getApplicationContext())
                .load(getResources().getString(R.string.glide_transmation_url_2))
                .transform(new GreyscaleTransformation(getActivity()), new BlurTransformation(getActivity()))
                .into(mImageViewTransmation2);

        // Usage of Glide Transformations
        with(getActivity().getApplicationContext())
                .load(getResources().getString(R.string.glide_transmation_url_3))
                .bitmapTransform(new jp.wasabeef.glide.transformations.BlurTransformation(getActivity(), 25))
                .into(mImageViewTransmation3);

        // animation
//        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.animation_small_enlarge);
//        Glide.with(getActivity().getApplicationContext())
//                .load(getResources().getString(R.string.glide_transmation_url_anim))
//                .animate(anim) // or R.anim.zoom_in
//                .into(mImageViewAnim);

        Glide
                .with(getActivity().getApplicationContext())
                .load(getResources().getString(R.string.glide_transmation_url_anim))
                .animate(animationObject)
                .into(mImageViewAnim);

    }


    ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            // if it's a custom view class, cast it here
            // then find subviews and do the animations
            // here, we just use the entire view for the fade animation
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(2500);
            fadeAnim.start();
        }
    };

    private void loadImageThumbnailRequest() {
        // setup Glide request without the into() method
        DrawableRequestBuilder<String> thumbnailRequest =
                Glide.with(getActivity().getApplicationContext())
                        .load(getResources().getString(R.string.glide_thumbnail_url));

        // pass the request as a a parameter to the thumbnail request
        Glide.with(getActivity().getApplicationContext())
                .load(getResources().getString(R.string.glide_single_img_url))
                .thumbnail(thumbnailRequest)
//                .thumbnail(0.5f)
                .into(mImageView6);
    }


    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            // do something with the bitmap
            // for demonstration purposes, let's just set it to an ImageView
            mImageView7.setImageBitmap(bitmap);
        }
    };

    private void loadImageSimpleTarget() {
        with(getActivity().getApplicationContext()) // could be an issue!
                .load(getResources().getString(R.string.glide_target_url_1))
                .asBitmap()
                .into(target);
    }

    private void glideDebugingTest() {
        with(getActivity().getApplicationContext()).load(getResources().getString(R.string.glide_single_img_debug_url))
                .listener(requestListener)
                .error(R.drawable.bg)
                .into(mImageViewDebuging);
    }


    private SimpleTarget target2 = new SimpleTarget<Bitmap>(250, 250) {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            mImageView8.setImageBitmap(bitmap);
        }
    };

    private void loadImageSimpleTargetApplicationContext() {
        with(getActivity().getApplicationContext()) // safer!
                .load(getResources().getString(R.string.glide_target_url_2))
                .asBitmap()
                .into(target2);
    }


    private void loadImageViewTarget() {
        FutureStudioView customView = (FutureStudioView) rootView.findViewById(R.id.custom_view);

        ViewTarget viewTarget = new ViewTarget<FutureStudioView, GlideDrawable>(customView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource.getCurrent());
            }
        };

        with(getActivity().getApplicationContext()) // safer!
                .load(getResources().getString(R.string.glide_target_url_3))
                .into(viewTarget);
    }


    public class IntegerVersionSignature implements Key {
        private int currentVersion;

        public IntegerVersionSignature(int currentVersion) {
            this.currentVersion = currentVersion;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof IntegerVersionSignature) {
                IntegerVersionSignature other = (IntegerVersionSignature) o;
                return currentVersion == other.currentVersion;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return currentVersion;
        }

        @Override
        public void updateDiskCacheKey(MessageDigest md) {
//            messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(signature).array());
        }
    }

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


    private void notifyTarget() {
        final RemoteViews rv = new RemoteViews(getActivity().getPackageName(), R.layout.remoteview_notification);

        rv.setImageViewResource(R.id.remoteview_notification_icon, R.mipmap.ic_launcher);

        rv.setTextViewText(R.id.remoteview_notification_headline, "Headline");
        rv.setTextViewText(R.id.remoteview_notification_short_message, "Short Message");

        // build notification
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setContent(rv)
                        .setPriority(NotificationCompat.PRIORITY_MIN);
        final Notification notification = mBuilder.build();


        // set big content view for newer androids
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = rv;
        }


        int NOTIFICATION_ID = new Random().nextInt();

        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, notification);


        notificationTarget = new NotificationTarget(
                getActivity(),
                rv,
                R.id.remoteview_notification_icon,
                notification,
                NOTIFICATION_ID);

        with(getActivity().getApplicationContext()) // safer!
                .load(getResources().getString(R.string.glide_target_url_3))
                .asBitmap()
                .into(notificationTarget);
    }


    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception

            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };


    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
