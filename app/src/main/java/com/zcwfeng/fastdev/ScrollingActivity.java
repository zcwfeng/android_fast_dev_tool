package com.zcwfeng.fastdev;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zcwfeng.componentlibs.BaseApplication;
import com.zcwfeng.componentlibs.surport.inject.ContentView;
import com.zcwfeng.componentlibs.surport.inject.ViewInject;
import com.zcwfeng.componentlibs.surport.utils.AntiEmulator;
import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.BindingData.MvvmDemoActivity;
import com.zcwfeng.fastdev.MediaSample.MediaLibUseDemo;
import com.zcwfeng.fastdev.binder.AIDLTestActivity;
import com.zcwfeng.fastdev.flyrefresh.FlyRefreshActivity;
import com.zcwfeng.fastdev.glide.GlideLibDemos;
import com.zcwfeng.fastdev.intent_ref.IntentReferenceActivity;
import com.zcwfeng.fastdev.renderscript.RenderScriptTestActivity;
import com.zcwfeng.fastdev.sample_okhttp.OkhttpUtilsMainActivity;
import com.zcwfeng.fastdev.secure.skb.ExamplesRSA_DESActivity;
import com.zcwfeng.fastdev.ui.activity.CoordinatorLayoutTestActivity;
import com.zcwfeng.fastdev.ui.activity.DampingScrollActivity;
import com.zcwfeng.fastdev.ui.activity.HttpRequestStudyActivity;
import com.zcwfeng.fastdev.ui.activity.ImageRefViewTestActivity;
import com.zcwfeng.fastdev.ui.activity.NDKDemoActivity;
import com.zcwfeng.fastdev.ui.activity.RefreshTestActivity;
import com.zcwfeng.fastdev.ui.activity.SlideHRecyclerViewTestActivity;
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
    @ViewInject(id = R.id.render_script_btn)
    Button testRenderScript;
    @ViewInject(id = R.id.binder_activity)
    Button testAIDLBinder;
    @ViewInject(id = R.id.glidelib_activity)
    Button testGlide;
    @ViewInject(id = R.id.skb_activity)
    Button testSKB;
    @ViewInject(id = R.id.store_start)
    Button testStore;
    @ViewInject(id = R.id.damping_scroll)
    Button testDamping;
    @ViewInject(id = R.id.flyrefresh)
    Button testFlyRefresh;
    @ViewInject(id = R.id.http_request)
    Button testHttpRequest;
    @ViewInject(id = R.id.custom_recyclerview)
    Button testRecyclerView;
    @ViewInject(id = R.id.custom_image_ref)
    Button testImage;


    InterstitialAd mInterstitialAd;
    Button mNewGameButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        UserProfileActivity.launch(this, UserProfileActivity.class);
    }

    public void doTestCalendar(View v) {
        boolean isEmu = AntiEmulator.CheckDeviceIDS(BaseApplication.getInstance())
                || AntiEmulator.checkPipes()
                || AntiEmulator.CheckEmulatorBuild(BaseApplication.getInstance())
                || AntiEmulator.CheckEmulatorFiles()
                || AntiEmulator.CheckImsiIDS(BaseApplication.getInstance())
                || AntiEmulator.CheckOperatorNameAndroid(BaseApplication.getInstance())
                || AntiEmulator.CheckPhoneNumber(BaseApplication.getInstance())
                || AntiEmulator.checkQEmuDriverFile();

        Toast.makeText(ScrollingActivity.this, "--" + String.valueOf(isEmu) + "----", Toast.LENGTH_SHORT).show();
        TestWidgetActivity.launch(ScrollingActivity.this);
    }

    public void doTestIntent(View v) {
        IntentReferenceActivity.launch(this);
    }

    public void doTestCoordnator(View v) {
        CoordinatorLayoutTestActivity.launch(ScrollingActivity.this, CoordinatorLayoutTestActivity.class);
    }


    public void doTestRefreshTestActivity(View v) {
        RefreshTestActivity.launch(ScrollingActivity.this);
    }

    public void testNdk(View v) {
        NDKDemoActivity.launch(ScrollingActivity.this);
    }


    public void doTestRenderscript(View V) {
        RenderScriptTestActivity.launch(ScrollingActivity.this);
    }

    public void doAIDLTest(View v) {
        AIDLTestActivity.launch(ScrollingActivity.this);
    }


    public void doTestGlide(View view) {
        GlideLibDemos.launch(ScrollingActivity.this);
    }

    public void doSKBTest(View view) {
        ExamplesRSA_DESActivity.launch(ScrollingActivity.this);
    }

    public void doTestFlyRefresh(View view) {
        FlyRefreshActivity.launch(ScrollingActivity.this);
    }

    public void doTestHttpRequest(View view) {
        HttpRequestStudyActivity.launch(ScrollingActivity.this);
    }

    public void doTestRecyclerView(View view) {
        SlideHRecyclerViewTestActivity.launch(ScrollingActivity.this, SlideHRecyclerViewTestActivity.class);
    }
    public void doTestImageRefView(View view) {
        ImageRefViewTestActivity.launch(this);
    }



    /**
     * http://market.android.com/details?id=<java包名>
     * 或者
     * market://details?id=<java包名>
     *
     * @param view
     */
    public void doStoreTest(View view) {


//        Uri uri = Uri.parse("market://details?id=" + "com.yinyuetai.ui");
//        Uri uri = Uri.parse("market://search?q=pname:" + "com.yinyuetai.ui");
//        Uri uri = Uri.parse("market://search?q=pub:" + "音悦台");
//        Uri uri = Uri.parse("market://search?q=" + "音悦台");
        Uri uri = Uri.parse("market://search?q=音 悦 pub:" + "音悦台");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

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

    public void doTestDampingScroll(View view) {
        launch(ScrollingActivity.this, DampingScrollActivity.class);
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Scrolling Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


////////////////////AD END//////////////////////////////////

}
