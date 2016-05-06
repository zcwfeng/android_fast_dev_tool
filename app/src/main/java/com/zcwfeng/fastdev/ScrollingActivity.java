package com.zcwfeng.fastdev;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.zcwfeng.componentlibs.surport.inject.ContentView;
import com.zcwfeng.componentlibs.surport.inject.ViewInject;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.BindingData.MvvmDemoActivity;
import com.zcwfeng.fastdev.MediaSample.MediaLibUseDemo;
import com.zcwfeng.fastdev.intent_ref.IntentReferenceActivity;
import com.zcwfeng.fastdev.renderscript.RenderScriptTestActivity;
import com.zcwfeng.fastdev.sample_okhttp.OkhttpUtilsMainActivity;
import com.zcwfeng.fastdev.ui.activity.CoordinatorLayoutTestActivity;
import com.zcwfeng.fastdev.ui.activity.NDKDemoActivity;
import com.zcwfeng.fastdev.ui.activity.RefreshTestActivity;
import com.zcwfeng.fastdev.ui.activity.RxJavaDemoActivity;
import com.zcwfeng.fastdev.ui.activity.UserProfileActivity;
import com.zcwfeng.fastdev.widgettest.TestWidgetActivity;

import java.util.Collections;
import java.util.List;

//import com.android.vending.billing.IInAppBillingService;

@ContentView(value = R.layout.activity_scrolling)
public class ScrollingActivity extends BaseActivity implements PlayStorePurchaseListener {
    @ViewInject(id = R.id.to_test_okhttp)
    Button okHttp;
    @ViewInject(id = R.id.user_profile_common_Activity)
    Button userProfileActivity;
    @ViewInject(id = R.id.calendar_test)
    Button testCalendar;
    @ViewInject(id = R.id.intent_test)
    Button testIntentRef;
    @ViewInject(id = R.id.coordnator_layout_btn)
    Button testCoordnatorLayotu;
    @ViewInject(id = R.id.testRefreshTestActivity)
    Button testRefreshTestActivity;
    @ViewInject(id = R.id.ndkdemo)
    Button testNdkDemo;
    @ViewInject(id = R.id.rxjavademo)
    Button testRxJava;
    @ViewInject(id = R.id.render_script_btn)
    Button testRenderScript;



    InterstitialAd mInterstitialAd;
    Button mNewGameButton;
    // 内置购买广告
//    public static final int BILLING_RESPONSE_RESULT_OK = 0;
//    private InterstitialAd interstitial;
//    private InAppBillingService mService;
//    private Button showAdButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/////////////start//////////
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestNewInterstitial();
//ad 1
        mNewGameButton = (Button) findViewById(R.id.newgame_button);

        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
//                    beginPlayingGame();
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
//                beginPlayingGame();
            }
        });


//ad 2
//        showAdButton = (Button) findViewById(R.id.showAd);
//        showAdButton.setEnabled(false);
//
//    // Create the interstitial ad and set Google Play store purchase parameters.
////        interstitial = new InterstitialAd(this);
//        // No need to set a public key for IAP ads.
//        interstitial.setPlayStorePurchaseParams(this, null);
//
//        // Put your ad unit ID here, e.g., "ca-app-pub-2412876219430673/4729615340"
//        interstitial.setAdUnitId("ca-app-pub-2412876219430673/4729615340");

        setInterstitialAdListener();

/////////////END//////////

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-3940256099942544/1033173712")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toSampleOkhttp(View v) {
        OkhttpUtilsMainActivity.launch(this);
    }

    public void doMvvmDemo(View v) {
        MvvmDemoActivity.launch(this);
    }

    public void doMediaSample(View view) {
        MediaLibUseDemo.launch(this, MediaLibUseDemo.class);
    }

    public void toUserProfileActivity(View v) {
        UserProfileActivity.launch(this,UserProfileActivity.class);
    }

    public void doTestCalendar(View v){
        TestWidgetActivity.launch(this);
    }

    public void doTestIntent(View v){
        IntentReferenceActivity.launch(this);}

    public void doTestCoordnator(View v) {
        CoordinatorLayoutTestActivity.launch(ScrollingActivity.this,CoordinatorLayoutTestActivity.class);
    }


    public void doTestRefreshTestActivity(View v) {
        RefreshTestActivity.launch(ScrollingActivity.this);
    }
    public void testNdk(View v) {
        NDKDemoActivity.launch(ScrollingActivity.this);
    }

    public void doRxJava(View v) {
        RxJavaDemoActivity.launch(ScrollingActivity.this);
    }

    public void doTestRenderscript(View V){
        RenderScriptTestActivity.launch(ScrollingActivity.this);}

    @Override
    public boolean isValidPurchase(String sku) {
        try {
            if (getOwnedProducts().contains(sku)) {
                // Handle the case if product is already purchased.
                return false;
            }
        } catch (RemoteException e) {
            return false;
        }
        return true;
    }

////////////////////AD Begin//////////////////////////////////

    private List getOwnedProducts() throws RemoteException {
        // Query for purchased items.
        // See http://developer.android.com/google/play/billing/billing_reference.html and
//        // http://developer.android.com/google/play/billing/billing_integrate.html
//        Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
//        int response = ownedItems.getInt("RESPONSE_CODE");
//        Log.i("Iap-Ad", "Response code of purchased item query");
//        if (response == 0) {
//            return ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
//        }
        return Collections.emptyList();
    }

    private void setInterstitialAdListener() {
        // Set an AdListener.
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mNewGameButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                mNewGameButton.setEnabled(false);
                // Optional: your custom code here.
            }
        });
    }

    public void displayInterstitial(View v) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void loadInterstitial(View v) {
        // Create ad request.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Begin loading interstitial ad.
        mInterstitialAd.loadAd(adRequest);
    }
    @Override
    public void onInAppPurchaseFinished(InAppPurchaseResult result) {
        Log.i("Iap-Ad", "onInAppPurchaseFinished Start");
        int resultCode = result.getResultCode();
        Log.i("Iap-Ad", "result code: " + resultCode);
//        String sku = result.getProductId();
//        if (resultCode == Activity.RESULT_OK) {
//            Log.i("Iap-Ad", "purchased product id: " + sku);
//            int responseCode = result.getPurchaseData().getIntExtra(
//                    "RESPONSE_CODE", BILLING_RESPONSE_RESULT_OK);
//            String purchaseData = result.getPurchaseData().getStringExtra("INAPP_PURCHASE_DATA");
//            Log.i("Iap-Ad", "response code: " + responseCode);
//            Log.i("Iap-Ad", "purchase data: " + purchaseData);
//
//            // Finish purchase and consume product.
//            result.finishPurchase();
//            // if (responseCode == BILLING_RESPONSE_RESULT_OK) {
//            // Optional: your custom process goes here, e.g., add coins after purchase.
//            //  }
//        } else {
//            Log.w("Iap-Ad", "Failed to purchase product: " + sku);
//        }
//        Log.i("Iap-Ad", "onInAppPurchaseFinished End");
    }

////////////////////AD END//////////////////////////////////

}
