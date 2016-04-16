package com.zcwfeng.fastdev.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.widget.CustomListView;
import com.zcwfeng.fastdev.ui.widget.refresh.RefreshLayout;

import java.util.ArrayList;

/**
 * Created by David.zhang on 16/4/16.
 * Description：
 */
public class RefreshTestActivity extends BaseActivity {
    RefreshLayout mOuterRefresh;

    public static void launch(Context from) {
        Intent intent = new Intent(from, RefreshTestActivity.class);
        from.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_refresh);



        mOuterRefresh = (RefreshLayout) findViewById(R.id.refreshLayout);
        mOuterRefresh.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(RefreshTestActivity.this, "刷新回调被调用了", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            strings.add("我是" + i);
        }

        CustomListView listView = (CustomListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));


        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOuterRefresh.setRefreshing(false);
            }
        });
    }
}
