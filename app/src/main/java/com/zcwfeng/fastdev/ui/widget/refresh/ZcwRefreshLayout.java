package com.zcwfeng.fastdev.ui.widget.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private final float DEFAULT_REFRESH_VIEW_MAX_HEIGHT = 40;    // 可拖动的默认最大值
    private final float DEFAULT_REFRESH_VIEW_HEIGHT = 20;         // 刷新控件的默认高度
    private int mRefreshViewMaxHeight;                  // 可拖动的最大值
    private int mRefreshViewHeight;                     // 刷新动画控件的高度
    private TranslateAnimation mTopAnim;
    private TranslateAnimation mBottomAnim;
    private ScaleAnimation mLeftAnim;
    private ScaleAnimation mRightAnim;

    private LinearLayout mBottomView;
    private LinearLayout mTopView;
    private LinearLayout mCenterLeftView;
    private LinearLayout mCenterRightView;
    private float mTransitionProgress;              // 动画进度 0.0 - 1.0
    private LinearLayout mCenterLayout;

    private PullRefreshView mPullRefreshView;

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
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.refreshlayout_zcw, null);
        if (mBottomView == null)
            mBottomView = (LinearLayout) view.findViewById(R.id.progress_anim_view1);
        if (mTopView == null)
            mTopView = (LinearLayout) view.findViewById(R.id.progress_anim_view2);
        if (mCenterLeftView == null)
            mCenterLeftView = (LinearLayout) view.findViewById(R.id.scale_view1);
        if (mCenterRightView == null)
            mCenterRightView = (LinearLayout) view.findViewById(R.id.scale_view2);
        if (mCenterLayout == null)
            mCenterLayout = (LinearLayout) view.findViewById(R.id.center_layout);

        mPullRefreshView = new PullRefreshView(context, attrs);
        LayoutParams params = new LayoutParams(UIUtils.getScreenWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mPullRefreshView.setLayoutParams(params);
        mPullRefreshView.invalidate();
        view.addView(mPullRefreshView);

        this.addView(view);

    }


    public void setRefreshScale() {
        mCenterLeftView.startAnimation(mLeftAnim);
        mCenterRightView.startAnimation(mRightAnim);

    }


    public void setVisible(int visible) {
        mCenterLayout.setVisibility(visible);
        mLeftAnim.cancel();
        mRightAnim.cancel();
    }

    public void startRefreshViewAnim() {
        if (mBottomAnim != null)
            mBottomView.startAnimation(mBottomAnim);
        if (mTopAnim != null)
            mTopView.startAnimation(mTopAnim);
    }

    public void cancelRefreshViewAnim() {
        mTopAnim.cancel();
        mBottomAnim.cancel();
    }

    public void setOnRefreshListener(RefreshLayout.OnRefreshListener listener) {
        mListener = listener;
    }

    public void resetValues() {
        mPullRefreshView.setVisibility(View.INVISIBLE);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    private RefreshLayout.OnRefreshListener mListener;


    /**
     * @param transitionProgress 进度，是一个0 ~ 1间的值
     */
    public void setTransitionProgress(float transitionProgress) {
        mPullRefreshView.setmTransitionProgress(transitionProgress);
    }

    private float d2x(float size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getContext().getResources().getDisplayMetrics());
    }


}
