package com.zcwfeng.fastdev.ui.widget.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.zcwfeng.componentlibs.surport.utils.UIUtils;

/**
 * Created by David.zhang on 16/4/17.
 * Description：
 */
public class PullRefreshView extends View {
    public void setmTransitionProgress(float mTransitionProgress) {
        this.mTransitionProgress = mTransitionProgress;
    }

    private float mTransitionProgress;              // 动画进度 0.0 - 1.0

    private int mLeftX;
    private int mLeftY;
    private int mRightX;
    private int mRightY;
    private int mGestorWidth;
    private int mGestorHeight;

    private int CELL_WIDTH = UIUtils.getScreenWidth() / 4;
    Paint mLeftPaint = new Paint();
    Paint mRightPaint = new Paint();

    public PullRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PullRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    private void initView() {
        mLeftX = CELL_WIDTH;
        mLeftY = 0;
        mRightX = CELL_WIDTH * 2;
        mRightY = 0;
        mGestorWidth = CELL_WIDTH;
        mGestorHeight = 40;

        mLeftPaint.setColor(Color.parseColor("#ffe400"));
        mRightPaint.setColor(Color.parseColor("#1ecfff"));
        mLeftPaint.setStyle(Paint.Style.FILL);
        mRightPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawContent(canvas);
    }

    public void drawContent(Canvas canvas) {

        Rect lReact = new Rect(mLeftX,mLeftY,mLeftX + mGestorWidth,mGestorHeight);
        canvas.drawRect(lReact, mLeftPaint);
        canvas.drawRect(mRightX, mRightY, mRightX+ mGestorWidth, mGestorHeight, mRightPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initValue(w,h,oldw,oldh);
    }

    private void initValue(int w, int h, int oldw, int oldh) {
        mGestorHeight = h;
        mGestorWidth = CELL_WIDTH + (int)(CELL_WIDTH * mTransitionProgress);
        mLeftX = CELL_WIDTH * 2 - mGestorWidth;

        if(mTransitionProgress == 1.0) {
            mLeftX =0;
            mGestorWidth = 0;
            mGestorHeight = 0;
        } else {
        }

    }
}
