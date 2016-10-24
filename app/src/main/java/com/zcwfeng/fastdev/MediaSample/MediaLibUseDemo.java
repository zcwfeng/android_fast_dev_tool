package com.zcwfeng.fastdev.MediaSample;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.android.tedcoder.wkvideoplayer.dlna.engine.DLNAContainer;
import com.android.tedcoder.wkvideoplayer.dlna.service.DLNAService;
import com.android.tedcoder.wkvideoplayer.model.Video;
import com.android.tedcoder.wkvideoplayer.model.VideoUrl;
import com.android.tedcoder.wkvideoplayer.util.DensityUtil;
import com.android.tedcoder.wkvideoplayer.view.MediaController;
import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;
import com.google.android.exoplayer.util.Util;
import com.yinyuetai.videolib.PlayerProxy;
import com.yinyuetai.videolib.YYTVideoView;
import com.zcwfeng.componentlibs.surport.utils.MediaUtils;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity_deprecated;
import com.zcwfeng.fastdev.R;

import java.util.ArrayList;


/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
//@ContentView(value = R.layout.activity_media_sample)
public class MediaLibUseDemo extends BaseActivity_deprecated implements View.OnClickListener {

//    @ViewInject(id = R.id.video_player_item_1)
//    private SuperVideoPlayer mSuperVideoPlayer;
//    @ViewInject(id = R.id.play_btn)
//    private View mPlayBtnView;
//    @ViewInject(id = R.id.cover_video_bg)
//    private View mCoverBgView;
//    @ViewInject(id = R.id.video_player_item_2)
//    private YYTVideoView mYYTVideoView;

    private SuperVideoPlayer mSuperVideoPlayer;
    private View mPlayBtnView;
    private View mCoverBgView;
    private YYTVideoView mYYTVideoView;

    public static void launch(Context context, Object... params) {
        Intent intent = new Intent(context, MediaLibUseDemo.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_sample);
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        mPlayBtnView = findViewById(R.id.play_btn);
        mCoverBgView = findViewById(R.id.cover_video_bg);
        mYYTVideoView = (YYTVideoView) findViewById(R.id.video_player_item_2);

        mPlayBtnView.setOnClickListener(this);

//        mPlayBtnViewYyt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPlayBtnViewYyt.setVisibility(View.GONE);
//                mYYTVideoView.setVisibility(View.VISIBLE);
//                mYYTVideoView.playVideo(PlayerProxy.PLAYER_VR,"http://video-player.720yun.com/@/5c529czdwua/pc_1469596419.mp4", Util.TYPE_OTHER,0);
//
//            }
//        });


        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
        startDLNAService();

        mGestureDetector = new GestureDetector(this, new MyGestureListener());
        try {
            MediaUtils.generateCover4Video("http://58.254.132.66/dd.yinyuetai.com/uploads/videos/common/D61E01478B129B2900ED167746C2BA81.mp4?sc=248154b5d767b8e2&br=576&rd=Android&uniqueId=ee343f05e05cf2b6748c1c77fcf3caeb",mCoverBgView);
        } catch (Exception e) {
            Log.e("zcw","获取封面异常");
            e.printStackTrace();
        }

        mYYTVideoView.setVisibility(View.VISIBLE);
        mYYTVideoView.playVideo(PlayerProxy.PLAYER_VR,"http://video-player.720yun.com/@/5c529czdwua/pc_1469596419.mp4", Util.TYPE_OTHER,0);

    }


    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.close();
            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {

        }
    };

    @Override
    public void onClick(View view) {
        mPlayBtnView.setVisibility(View.GONE);
        mSuperVideoPlayer.setVisibility(View.VISIBLE);
        mSuperVideoPlayer.setAutoHideController(false);

        Video video = new Video();
        VideoUrl videoUrl1 = new VideoUrl();
        videoUrl1.setFormatName("720P");
        videoUrl1.setFormatUrl("http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_1080x720_20mb.mp4");
        VideoUrl videoUrl2 = new VideoUrl();
        videoUrl2.setFormatName("480P");
        videoUrl2.setFormatUrl("http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_720x480_20mb.mp4");
        ArrayList<VideoUrl> arrayList1 = new ArrayList<>();
        arrayList1.add(videoUrl1);
        arrayList1.add(videoUrl2);
        video.setVideoName("测试视频一");
        video.setVideoUrl(arrayList1);

        Video video2 = new Video();
        VideoUrl videoUrl3 = new VideoUrl();
        videoUrl3.setFormatName("720P");
        videoUrl3.setFormatUrl("http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_1080x720_10mb.mp4");
        VideoUrl videoUrl4 = new VideoUrl();
        videoUrl4.setFormatName("480P");
        videoUrl4.setFormatUrl("http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_720x480_10mb.mp4");
        ArrayList<VideoUrl> arrayList2 = new ArrayList<>();
        arrayList2.add(videoUrl3);
        arrayList2.add(videoUrl4);
        video2.setVideoName("测试视频二");
        video2.setVideoUrl(arrayList2);


        Video video3 = new Video();
        VideoUrl videoUrl5 = new VideoUrl();
        videoUrl5.setFormatName("720P");
        videoUrl5.setFormatUrl("http://k.youku.com/player/getFlvPath/sid/94521483395192097afba_00/st/flv/fileid/0300020A00568D3991EDA5080D48DDCE2DBB8A-D962-47F7-32A1-44A2D8402574?K=e4e0c8b6eaf163042412770b&hd=0&ts=378&ymovie=1&r=/3sLngL0Q6CXymAIiF9JUfR5MDecwxp/gSVk/o8apWJ3KUkaGrqktKh7cO9ZZoqYN5iGQUM9dNrj6YzDV+fl4ItT1jgFQWklPMdoPLHx42dWA+6ZvS0h8SjrDz3ad1iCTOZUWKIkTfGGnYbSfuxBAy9D5Jg2MI0ix1Za2ptrJbk=&oip=2043096855&sid=94521483395192097afba&token=3433&did=3ac5e7b2063ed661fa1285eee9bb5c36&ev=1&ctype=20&ep=GGmEELCnoNUX3YoHvSJi2XBRc%2BkNLgYVswEy0bUSdisWUmJw2pObDQti0WRptEARQJ3p3Te1b36S1f4N7RfFpFrcrvdVfHP8NUdyD68D78Xlyq7yyHQQgO4mRHyU4Chz");
        VideoUrl videoUrl6 = new VideoUrl();
        videoUrl6.setFormatName("480P");
        videoUrl6.setFormatUrl("http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_720x480_10mb.mp4");
        ArrayList<VideoUrl> arrayList3 = new ArrayList<>();
        arrayList3.add(videoUrl5);
        arrayList3.add(videoUrl6);
        video3.setVideoName("测试优酷");
        video3.setVideoUrl(arrayList3);


        Video video4 = new Video();
        VideoUrl videoUrl7 = new VideoUrl();
        videoUrl7.setFormatName("标清");
        videoUrl7.setFormatUrl("http://58.254.132.66/dd.yinyuetai.com/uploads/videos/common/D61E01478B129B2900ED167746C2BA81.mp4?sc=248154b5d767b8e2&br=576&rd=Android&uniqueId=ee343f05e05cf2b6748c1c77fcf3caeb");
        VideoUrl videoUrl8 = new VideoUrl();
        videoUrl8.setFormatName("高清");
        videoUrl8.setFormatUrl("http://58.254.132.66/hc.yinyuetai.com/uploads/videos/common/9AAA01478AF509749A7332D32A7AE83B.flv?sc=7580d8ce160c75d7&br=777&rd=Android&uniqueId=ee343f05e05cf2b6748c1c77fcf3caeb");
        VideoUrl videoUrl9 = new VideoUrl();
        videoUrl9.setFormatName("超清");
        videoUrl9.setFormatUrl("http://58.254.132.66/hd.yinyuetai.com/uploads/videos/common/0BC101478B129B3052F6B709FC858183.flv?sc=0588f178db7d6a0e&br=1097&rd=Android&uniqueId=ee343f05e05cf2b6748c1c77fcf3caeb");


        ArrayList<VideoUrl> arrayList4 = new ArrayList<>();
        arrayList4.add(videoUrl7);
        arrayList4.add(videoUrl8);
        arrayList4.add(videoUrl9);
        video4.setVideoName("测试yyt");
        video4.setVideoUrl(arrayList4);


        ArrayList<Video> videoArrayList = new ArrayList<>();
        videoArrayList.add(video4);
//        videoArrayList.add(video3);
//        videoArrayList.add(video);
//        videoArrayList.add(video2);

        mSuperVideoPlayer.loadMultipleVideo(videoArrayList, 0, 0, 0);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDLNAService();
        mYYTVideoView.pause();
        mYYTVideoView.destroyDrawingCache();
    }

    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }




    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }

    private void startDLNAService() {
        // Clear the device container.
        DLNAContainer.getInstance().clear();
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        startService(intent);
    }

    private void stopDLNAService() {
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSuperVideoPlayer.close();
        finish();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    private GestureDetector mGestureDetector;

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }



        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        /**
         * 双击
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        /**
         * 滑动
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (true) {
                float mOldX = e1.getX();
                float mOldY = e1.getY();
                float x = e2.getX();
                float y = e2.getY();
//                if (mIsScrollH || (Math.abs(y - mOldY) > Math.abs(x - mOldX) && !mIsScrollV)) {//竖着滑动
                Log.e("zcw","响应了垂直滑动事件~~~~~~~");

            }
            return super.onScroll(e1,e2,distanceX,distanceY);
        }
    }
}
