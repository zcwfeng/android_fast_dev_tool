package com.zcwfeng.fastdev.ui.adapter;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;

import com.zcwfeng.fastdev.ui.fragment.BaseFragment;

import java.util.List;
import java.util.WeakHashMap;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private WeakHashMap<String, BaseFragment> mListFragments;
    private List<String> mListTitle;//tab名称列表

    public MyViewPagerAdapter(android.support.v4.app.FragmentManager fm, WeakHashMap<String, BaseFragment> listFragments, List<String> listTitle) {
        super(fm);
        mListFragments = listFragments;
        mListTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mListFragments.get(mListTitle.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable dImage = new ColorDrawable();
        dImage.setBounds(0, 0, dImage.getMinimumWidth(), dImage.getIntrinsicHeight());
        SpannableString sp = new SpannableString("" + mListTitle.get(position));
        sp.setSpan(dImage, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }
}