package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.adapter.MyViewPagerAdapter;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.database.GreenDaoFragment;
import com.zcwfeng.fastdev.ui.fragment.database.OriginFragment;
import com.zcwfeng.fastdev.ui.fragment.database.RealmFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class DatabasesActivity extends BaseActivity {
    private TabLayout mTabTitle;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private List<String> mListTitle;//tab名称列表
    private WeakHashMap<String, BaseFragment> mListFragments;
    private RealmFragment mRealmFragment;
    private GreenDaoFragment mGreenDaoFragment;
    private OriginFragment mOriginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container_template);
        setToolbar(null,"");
        initViews();
    }

    private void initViews() {
        mListTitle = new ArrayList<>();
        mListTitle.add("Realm");
        mListTitle.add("GreenDao");
        mListTitle.add("原始Origin");
        mRealmFragment = RealmFragment.newInstance();
        mGreenDaoFragment = GreenDaoFragment.newInstance();
        mOriginFragment = OriginFragment.newInstance();
        mListFragments = new WeakHashMap<>();
        mListFragments.put(mListTitle.get(0), mRealmFragment);
        mListFragments.put(mListTitle.get(1), mGreenDaoFragment);
        mListFragments.put(mListTitle.get(2), mOriginFragment);
        mTabTitle = (TabLayout) findViewById(R.id.sliding_tabs);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mListFragments, mListTitle);
        mViewPager = (ViewPager) findViewById(R.id.template_viewpager);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mAdapter);
        mTabTitle.setTabMode(TabLayout.MODE_FIXED);
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(0)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(1)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(2)));
        mTabTitle.setupWithViewPager(mViewPager);
        mTabTitle.setClipToPadding(true);


    }


}
