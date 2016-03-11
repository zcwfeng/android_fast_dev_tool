package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.widget.coordinatorlayout.CustomSwipeRefreshLayout;

/**
 * Created by David.zhang on 16/3/11.
 * Description：
 */
public class CoordinatorLayoutTestActivity extends BaseActivity {
    private CustomSwipeRefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coodinatorlayout);
        refreshLayout = (CustomSwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeResources(R.color.comm_blue);
        refreshLayout.setOnRefreshListener(() -> {
            test();
        });

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> refreshLayout.setEnabled(verticalOffset == 0));


        mRecyclerView = (RecyclerView) findViewById(R.id.fragment_footprint_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    private void test() {
        refreshLayout.setRefreshing(false);
    }
}
