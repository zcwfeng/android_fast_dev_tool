package com.zcwfeng.fastdev.ui.widget.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zcwfeng.componentlibs.surport.utils.UIUtils;
import com.zcwfeng.fastdev.R;

/**
 * Created by David.zhang on 16/4/16.
 * Description：
 */
public class ZcwRefreshLayout extends RelativeLayout {

    private final float DEFAULT_REFRESH_VIEW_MAX_HEIGHT = 300;    // 可拖动的默认最大值
    private final float DEFAULT_REFRESH_VIEW_HEIGHT = 50;         // 刷新控件的默认高度
    private int mRefreshViewMaxHeight;                  // 可拖动的最大值
    private int mRefreshViewHeight;                     // 刷新动画控件的高度
    private TranslateAnimation mTopAnim;
    private TranslateAnimation mBottomAnim;
    private ScaleAnimation mLeftAnim;
    private ScaleAnimation mRightAnim;
    private boolean mRefreshing;// 是否处于刷新状态

    private LinearLayout mBottomView;
    private LinearLayout mTopView;
    private LinearLayout mCenterLeftView;
    private LinearLayout mCenterRightView;


    public ZcwRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RefreshLayout, 0, 0);
            mRefreshViewMaxHeight = (int) a.getDimension(R.styleable.RefreshLayout_RefreshViewMaxHeight, d2x(DEFAULT_REFRESH_VIEW_MAX_HEIGHT));
            mRefreshViewHeight = (int) a.getDimension(R.styleable.RefreshLayout_RefreshViewHeight, d2x(DEFAULT_REFRESH_VIEW_HEIGHT));

            a.recycle();
        }

        initRefreshView(context, attrs);
        initAnimation();
    }

    public ZcwRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RefreshLayout, 0, 0);
            mRefreshViewMaxHeight = (int) a.getDimension(R.styleable.RefreshLayout_RefreshViewMaxHeight, d2x(DEFAULT_REFRESH_VIEW_MAX_HEIGHT));
            mRefreshViewHeight = (int) a.getDimension(R.styleable.RefreshLayout_RefreshViewHeight, d2x(DEFAULT_REFRESH_VIEW_HEIGHT));

            a.recycle();
        }

        initRefreshView(context, attrs);
        initAnimation();
    }


    private void initAnimation() {
        mTopAnim = new TranslateAnimation(
                0, -UIUtils.getScreenWidth(), 0, 0);
        mBottomAnim = new TranslateAnimation(UIUtils.getScreenWidth(), 0, 0, 0);
        mTopAnim.setDuration(3000);
        mBottomAnim.setDuration(3000);
        mTopAnim.setRepeatCount(Animation.INFINITE);
        mBottomAnim.setRepeatCount(Animation.INFINITE);


        mLeftAnim = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mLeftAnim.setDuration(3000);
        mLeftAnim.setRepeatCount(Animation.INFINITE);

        mRightAnim = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRightAnim.setDuration(3000);
        mRightAnim.setRepeatCount(Animation.INFINITE);


    }

    private void initRefreshView(Context context, AttributeSet attrs) {

    }


    public void setRefreshing(boolean refreshing) {

//        if (refreshing && !mRefreshing) {
//            mRefreshing = true;
//            mListView.setTranslationY(mRefreshViewHeight);
//            mRefreshInnerLayout.getLayoutParams().height = mRefreshViewHeight;
//            requestLayout();
//            mRefreshInnerLayout.setOnRefreshListener(null);
//            mRefreshInnerLayout.setMode(RefreshView.MODE_SETUP_5);
//        } else if (mRefreshing != refreshing) {
//            mRefreshing = false;
//            mPreListViewY = 0;
//            startRefreshViewBackTopAnim();
//        }

        startRefreshViewBackTopAnim();

    }


    public void setRefreshScale() {
        mTopAnim.cancel();
        mBottomAnim.cancel();

        mCenterLeftView.startAnimation(mLeftAnim);
        mCenterRightView.startAnimation(mRightAnim);

    }

    private void startRefreshViewBackTopAnim() {
        mBottomView.startAnimation(mBottomAnim);
        mTopView.startAnimation(mTopAnim);
    }


    public interface OnRefreshListener {
        void onRefresh();
    }

    private OnRefreshListener mListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBottomView = (LinearLayout) findViewById(R.id.progress_anim_view1);
        mTopView = (LinearLayout) findViewById(R.id.progress_anim_view2);
        mCenterLeftView = (LinearLayout) findViewById(R.id.scale_view1);
        mCenterRightView = (LinearLayout) findViewById(R.id.scale_view2);

    }


    private float d2x(float size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getContext().getResources().getDisplayMetrics());
    }

}
