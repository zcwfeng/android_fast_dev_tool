package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.adapter.MyViewPagerAdapter;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.download.NormalDownloadFragment;
import com.zcwfeng.fastdev.ui.fragment.download.WeexDownloadFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * 下载总结
 */
public class DownloadActivity extends BaseActivity {
    private TabLayout mTabTitle;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private List<String> mListTitle;//tab名称列表
    private WeakHashMap<String, BaseFragment> mListFragments;
    private WeexDownloadFragment mWeexDownloadFragment;
    private NormalDownloadFragment mNormalDownloadFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container_template);
        setToolbar((Toolbar) findViewById(R.id.toolBar),"");
        initViews();
    }

    private void initViews() {
        mListTitle = new ArrayList<>();
        mListTitle.add("Weex下载");
        mListTitle.add("其他下载");
        mWeexDownloadFragment = WeexDownloadFragment.newInstance();
        mNormalDownloadFragment = NormalDownloadFragment.newInstance();
        mListFragments = new WeakHashMap<>();
        mListFragments.put(mListTitle.get(0), mWeexDownloadFragment);
        mListFragments.put(mListTitle.get(1), mNormalDownloadFragment);
        mTabTitle = (TabLayout) findViewById(R.id.sliding_tabs);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mListFragments, mListTitle);
        mViewPager = (ViewPager) findViewById(R.id.template_viewpager);
        mViewPager.setAdapter(mAdapter);
        mTabTitle.setTabMode(TabLayout.MODE_FIXED);
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(0)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(1)));
        mTabTitle.setupWithViewPager(mViewPager);
        mTabTitle.setClipToPadding(true);
    }


}
