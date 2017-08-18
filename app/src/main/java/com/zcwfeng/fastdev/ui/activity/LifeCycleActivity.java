package com.zcwfeng.fastdev.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudtech.ads.core.CTAdEventListener;
import com.cloudtech.ads.core.CTAdvanceNative;
import com.cloudtech.ads.core.CTImageRatioType;
import com.cloudtech.ads.core.CTNative;
import com.cloudtech.ads.core.CTService;
import com.cloudtech.ads.vo.AdsNativeVO;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.basic.Config;

import static com.baidu.mapapi.BMapManager.getContext;


public class LifeCycleActivity extends BaseActivity implements View.OnClickListener,IntefaceChild {
    final String TAG = LifeCycleActivity.class.getSimpleName();
    //    private AnimateNativeAdViewLayout mCurrentNativeLayout;
    private LinearLayout adview_container;


    private InterstitialAd mInterstitialAd;//插屏广告

    private RelativeLayout parent;

    private RelativeLayout container;
    ViewGroup adLayout;
    ViewGroup containerYeah;
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


        parent = (RelativeLayout) findViewById(R.id.ad_parent);

        container = new RelativeLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(params);
        initView();
        findViewById(R.id.click_close).setOnClickListener(this);


        containerYeah =  (ViewGroup) findViewById(R.id.container_yeahmobi);
        adLayout = (ViewGroup)View.inflate(this,R.layout.advance_native_layout, null);
        findViewById(R.id.yeahmobi_banner).setOnClickListener(this);
        findViewById(R.id.yeahmobi_native).setOnClickListener(this);
        findViewById(R.id.yeahmobi_interstitial).setOnClickListener(this);
        findViewById(R.id.yeahmobi_video).setOnClickListener(this);




    }

    private void initView() {
        final RelativeLayout ad_close_layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.ad_close_layout, null);


        container.addView(ad_close_layout);
        parent.addView(container);

    }


    @Override
    public void onGetACCESS_FINE_LOCATIONPermissions() {
        super.onGetACCESS_FINE_LOCATIONPermissions();


    }

    private void testFacebookAds() {
//
    }

    private void testYeahmobiVideo(){



    }

    private void testYeahmobiInterstitial(){
        CTService.preloadInterstitial(Config.slotIdInterstitial,true,false,this,
                new MyCTAdEventListener(){

                    @Override
                    public void onInterstitialLoadSucceed(CTNative result) {
                        super.onInterstitialLoadSucceed(result);
                    }

                    @Override
                    public void onAdviewGotAdSucceed(CTNative result){
                        if (result != null && result.isLoaded()){
                            CTService.showInterstitial(result);
                        }
                        super.onAdviewGotAdSucceed(result);
                    }

                    @Override
                    public void onAdviewClosed(CTNative result) {

                        super.onAdviewClosed(result);
                    }

                });
    }

    private void testYeahmobiNative(){
        CTService.getAdvanceNative(Config.slotIdNative, this, CTImageRatioType.RATIO_19_TO_10,
                new MyCTAdEventListener(){
                    @Override
                    public void onAdviewGotAdSucceed(CTNative result) {
                        if (result == null){
                            return;
                        }
                        CTAdvanceNative ctAdvanceNative = (CTAdvanceNative) result;
                        showAd(ctAdvanceNative);
                        super.onAdviewGotAdSucceed(result);
                    }

                    @Override
                    public void onAdviewGotAdFail(CTNative result) {
                        super.onAdviewGotAdFail(result);
                    }

                    @Override
                    public void onAdviewClicked(CTNative result) {
                        super.onAdviewClicked(result);
                    }

                });
    }

    private void testYeahmobiBanner(){
        CTService.getBanner(Config.slotIdBanner, false, this,
                new MyCTAdEventListener(){

                    @Override
                    public void onAdviewGotAdSucceed(CTNative result) {
                        if (result != null){
                            containerYeah.removeAllViews();
                            containerYeah.addView(result);
                        }
                        super.onAdviewGotAdSucceed(result);
                    }

                    @Override
                    public void onAdviewGotAdFail(CTNative result) {
                        super.onAdviewGotAdFail(result);
                    }

                    @Override
                    public void onAdviewClicked(CTNative result) {
                        super.onAdviewClicked(result);
                    }

                    @Override
                    public void onAdviewClosed(CTNative result) {
                        containerYeah.removeAllViews();
                        super.onAdviewClosed(result);
                    }
                });
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
            case R.id.click_close:
                reload();
                break;
            case R.id.yeahmobi_banner:
                testYeahmobiBanner();
                break;
            case R.id.yeahmobi_native:
                testYeahmobiNative();
                break;
            case R.id.yeahmobi_interstitial:
                testYeahmobiInterstitial();
                break;
            case R.id.yeahmobi_video:
                testYeahmobiVideo();
                break;
        }
    }

    private void reload() {

        if(container != null) {
            ViewGroup group = (ViewGroup) container.getParent();
            if(group != null)
                group.removeAllViews();


            parent.addView(container);
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



    private void showAd(CTAdvanceNative ctAdvanceNative) {
        ImageView img = (ImageView) adLayout.findViewById(R.id.iv_img);
        ImageView icon = (ImageView) adLayout.findViewById(R.id.iv_icon);
        TextView title = (TextView)adLayout.findViewById(R.id.tv_title);
        TextView desc = (TextView)adLayout.findViewById(R.id.tv_desc);
        TextView click = (TextView)adLayout.findViewById(R.id.bt_click);
        ImageView ad_choice_icon = (ImageView)adLayout.findViewById(R.id.ad_choice_icon);

        // use your ImageLoader to show the image
        String imageUrl = ctAdvanceNative.getImageUrl();
        String iconUrl = ctAdvanceNative.getIconUrl();

        // use sdk api to show the preloaded image
//        ctAdvanceNative.setIconImage(icon);
//        ctAdvanceNative.setLargeImage(img);

        title.setText(ctAdvanceNative.getTitle());
        desc.setText(ctAdvanceNative.getDesc());
        click.setText(ctAdvanceNative.getButtonStr());
        ad_choice_icon.setImageURI(Uri.parse(ctAdvanceNative.getAdChoiceIconUrl()));

        // Mandatory. Add the customized ad layout to ad container.
        ctAdvanceNative.addADLayoutToADContainer(adLayout);
        // Optional. Set the ad click area,the default is the whole ad layout.
        ctAdvanceNative.registeADClickArea(adLayout);

        ad_choice_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //
            }
        });

        containerYeah.removeAllViews();
        containerYeah.addView(ctAdvanceNative);
    }

    @Override
    public void testParent() {

    }

    @Override
    public void testChild() {

    }


    public class MyCTAdEventListener implements CTAdEventListener {

        @Override
        public void onAdviewGotAdSucceed(CTNative result) {
            showMsg("onAdviewGotAdSucceed");
        }

        @Override
        public void onAdsVoGotAdSucceed(AdsNativeVO result) {
            showMsg("onAdsVoGotAdSucceed");
        }

        @Override
        public void onInterstitialLoadSucceed(CTNative result) {
            showMsg("onInterstitialLoadSucceed");
        }

        @Override
        public void onAdviewGotAdFail(CTNative result) {
            showMsg(result.getErrorsMsg());
            Log.i("sdksample", "errorMsg:" + result.getErrorsMsg());
        }

        @Override
        public void onAdviewIntoLandpage(CTNative result) {
            showMsg("onAdviewIntoLandpage");
        }

        @Override
        public void onStartLandingPageFail(CTNative result) {
            showMsg("onStartLandingPageFail");
        }

        @Override
        public void onAdviewDismissedLandpage(CTNative result) {
            showMsg("onAdviewDismissedLandpage");
        }

        @Override
        public void onAdviewClicked(CTNative result) {
            showMsg("onAdviewClicked");
        }

        @Override
        public void onAdviewClosed(CTNative result) {
            showMsg("onAdviewClosed");
        }

        @Override
        public void onAdviewDestroyed(CTNative result) {
            showMsg("onAdviewDestroyed");
        }


        private void showMsg(String msg){
            Toast.makeText(LifeCycleActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}