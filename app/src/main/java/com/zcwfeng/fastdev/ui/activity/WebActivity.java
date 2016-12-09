package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.basic.Config;
import com.zcwfeng.fastdev.basic.MyApplication;

/**
 * Created by zcw on 2016/12/9.
 */

public class WebActivity extends BaseActivity {

    private WebView mWebView;
    private String mWebUrl;
    private TextView mTvProgress;

    public static void launch(Context context,String url) {
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(Config.WEB_ACTIVITY_URL,url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setToolbar(null,"");
        mWebView = (WebView) findViewById(R.id.webview);
        mTvProgress = (TextView) findViewById(R.id.tv_progress);

        if (getIntent() != null) {
            mWebUrl = getIntent().getStringExtra(Config.WEB_ACTIVITY_URL);
        }

        initX5WebviewSetting();
    }

    private void initX5WebviewSetting() {
        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mTvProgress.setVisibility(View.GONE);
                } else {
                    mTvProgress.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams lp = mTvProgress.getLayoutParams();
                    lp.width = MyApplication.SCREEN_WIDTH * newProgress / 100;
                }
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        mWebView.loadUrl(mWebUrl);

    }
}
