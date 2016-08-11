package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.httplibs.retrofit.api.httpsapi.ApiService;
import com.zcwfeng.httplibs.retrofit.api.response.GetIpInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by zcw on 16/6/27.
 */

public class HttpRequestStudyActivity extends BaseActivity {
    private static final String ENDPOINT = "http://ip.taobao.com";
    private TextView mTvContent;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_request_study);

        mTvContent = (TextView) findViewById(R.id.tv_content);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);

                mProgressBar.setVisibility(View.VISIBLE);

                Call<GetIpInfoResponse> call = apiService.getIpInfo("63.223.108.42");
                call.enqueue(new Callback<GetIpInfoResponse>() {


                    @Override
                    public void onResponse(Call<GetIpInfoResponse> call, Response<GetIpInfoResponse> response) {
                        mProgressBar.setVisibility(View.GONE);
                        GetIpInfoResponse getIpInfoResponse = response.body();
                        mTvContent.setText(getIpInfoResponse.data.country);
                    }

                    @Override
                    public void onFailure(Call<GetIpInfoResponse> call, Throwable t) {
                        mProgressBar.setVisibility(View.GONE);
                        mTvContent.setText(t.getMessage());
                    }

                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public static void launch(Context scrollingActivity) {
        Intent intent = new Intent(scrollingActivity,HttpRequestStudyActivity.class);
        scrollingActivity.startActivity(intent);
    }
}
