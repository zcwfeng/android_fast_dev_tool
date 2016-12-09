package com.zcwfeng.fastdev.demos.zoomimageheader.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.demos.zoomimageheader.CtrlLinearLayoutManager;
import com.zcwfeng.fastdev.demos.zoomimageheader.MarginConfig;
import com.zcwfeng.fastdev.demos.zoomimageheader.MyListAdapter;
import com.zcwfeng.fastdev.demos.zoomimageheader.ZoomHeaderView;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;

import java.util.ArrayList;

/**
 * Created by zcw on 2016/12/9.
 */

public class ZoomImageHeaderActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    private ZoomHeaderView mZoomHeader;
    private boolean isFirst = true;
    private RelativeLayout mBottomView;
    public static int bottomY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomimageheader);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mZoomHeader = (ZoomHeaderView) findViewById(R.id.zoomHeader);
        mViewPager.setAdapter(new Adapter());
        mViewPager.setOffscreenPageLimit(4);
        CtrlLinearLayoutManager layoutManager = new CtrlLinearLayoutManager(this);

        //未展开禁止滑动
        layoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new MyListAdapter());
        mRecyclerView.setAlpha(0);
        mBottomView = (RelativeLayout) findViewById(R.id.rv_bottom);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isFirst) {

            for (int i = 0; i < mViewPager.getChildCount(); i++) {
                View v = mViewPager.getChildAt(i).findViewById(R.id.ll_bottom);
                v.setY(mViewPager.getChildAt(i).findViewById(R.id.imageView).getHeight());
                v.setX(MarginConfig.MARGIN_LEFT_RIGHT);
                //触发一次dependency变化，让按钮归位
                mZoomHeader.setY(mZoomHeader.getY() - 1);
                isFirst = false;
            }
        }

        //隐藏底部栏]
        bottomY = (int) mBottomView.getY();
        mBottomView.setTranslationY(mBottomView.getY() + mBottomView.getHeight());
        mZoomHeader.setmBottomView(mBottomView, bottomY);

    }

    class Adapter extends PagerAdapter {
        public Adapter() {
            views = new ArrayList<>();
            views.add((RelativeLayout) View.inflate(ZoomImageHeaderActivity.this, R.layout.item_img, null));
            views.get(0).findViewById(R.id.btn_buy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ZoomImageHeaderActivity.this, "buy", Toast.LENGTH_SHORT).show();
                }
            });
            views.add((RelativeLayout) View.inflate(ZoomImageHeaderActivity.this, R.layout.item_img, null));

            views.add((RelativeLayout) View.inflate(ZoomImageHeaderActivity.this, R.layout.item_img, null));

        }

        private ArrayList<RelativeLayout> views;
        private int[] imgs = {R.drawable.pizza, R.drawable.pic2, R.drawable.pic3};

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            views.get(position).findViewById(R.id.imageView).setBackgroundResource(imgs[position]);
            container.addView(views.get(position));


            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        if (mZoomHeader.isExpand()) {
            mZoomHeader.restore(mZoomHeader.getY());
        } else {
            finish();
        }

    }
}

