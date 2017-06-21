package com.zcwfeng.fastdev.ui.activity;

import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.eftimoff.androipathview.PathView;
import com.flurry.android.FlurryAgent;
import com.zcwfeng.fastdev.R;

public class SplashActivity extends BaseActivity {
    ImageView mSplashBg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestREAD_PHONE_STATEPermissions();




        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                BaseActivity.launch(SplashActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        }.sendEmptyMessageDelayed(0, 3000);


        final PathView pathView = (PathView) findViewById(R.id.pathView);
        final PathView pathViewIron = (PathView) findViewById(R.id.pathView_iron);
        final Path path = makeConvexArrow(50, 100);
        pathView.setPath(path);
        pathView.setFillAfter(true);
        pathView.useNaturalColors();

        pathView.getPathAnimator().
//                pathView.getSequentialPathAnimator().
        delay(100).
                duration(1500).
                interpolator(new AccelerateDecelerateInterpolator()).
                start();

        pathViewIron.setPath(path);
        pathViewIron.setFillAfter(true);
        pathViewIron.useNaturalColors();

        pathViewIron.getPathAnimator().
//                pathView.getSequentialPathAnimator().
        delay(50).
                duration(1000).
                interpolator(new AccelerateDecelerateInterpolator()).
                start();
    }

    private Path makeConvexArrow(float length, float height) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        return path;
    }


    @Override
    protected void onStart() {
        FlurryAgent.onStartSession(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        FlurryAgent.onEndSession(this);
        super.onStop();
    }

    @Override
    public void onGetCAMERAPermissions() {
        super.onGetCAMERAPermissions();
    }

    @Override
    public void onGetEXTERNALPermissions() {
        super.onGetEXTERNALPermissions();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onGetREAD_PHONE_STATEPermissions() {
        super.onGetREAD_PHONE_STATEPermissions();

    }

    @Override
    public void onGetRECORD_AUDIOPermissions() {
        super.onGetRECORD_AUDIOPermissions();
    }
}
