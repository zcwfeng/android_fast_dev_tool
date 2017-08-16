package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.zcwfeng.fastdev.R;


public class LifeCycleActivity extends BaseActivity implements View.OnClickListener {
    final String TAG = LifeCycleActivity.class.getSimpleName();
    //    private AnimateNativeAdViewLayout mCurrentNativeLayout;
    private LinearLayout adview_container;
    String fbId = "1232650820156535_1287061231382160";


    private InterstitialAd mInterstitialAd;//插屏广告

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ads);
        MobileAds.initialize(this, getString(R.string.my_app_admob_app_id));

        requestACCESS_COARSE_LOCATIONPermissions();
        requestACCESS_FINE_LOCATIONPermissions();


        setToolbar(null, "广告");
        findViewById(R.id.Admob_ads).setOnClickListener(this);
        findViewById(R.id.Admob_interstitial).setOnClickListener(this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5553031321204081/1018452640");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    @Override
    public void onGetACCESS_FINE_LOCATIONPermissions() {
        super.onGetACCESS_FINE_LOCATIONPermissions();


    }

    private void testFacebookAds() {
//
    }

    private void testAdmob() {
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed");
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.e(TAG, "onStart,The activity is about to become visible");

    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.e(TAG, "onResume,The activity has become visible (it is now \"resumed\")");

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.e(TAG, "onPause,Another activity is taking focus (this activity is about to be \"paused\"");

    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.e(TAG, "onStop,The activity is no longer visible (it is now \"stopped\"");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.e(TAG, "onDestroy,The activity is about to be destroyed.");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.facebook_ads:
                testFacebookAds();
                break;
            case R.id.baidu_ads:

                break;
            case R.id.Admob_ads:
                testAdmob();
                break;
            case R.id.Admob_interstitial:
                testInterstitial();
                break;
            case R.id.Altamob_ads:
                break;
        }
    }

    private void testInterstitial() {

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("testInterstitial", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("testInterstitial", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("testInterstitial", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("testInterstitial", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("testInterstitial", "onAdClosed");
                new AdRequest.Builder().addTestDevice("2273A70C2D3F027727AE5AEFBDD10507").build();
            }
        });




        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}