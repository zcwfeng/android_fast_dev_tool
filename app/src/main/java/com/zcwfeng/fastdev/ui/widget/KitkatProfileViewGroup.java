package com.zcwfeng.fastdev.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.zcwfeng.componentlibs.surport.utils.Logger;
import com.zcwfeng.componentlibs.surport.utils.SystemBarUtils;


public class KitkatProfileViewGroup extends FrameLayout {
    private final static String TAG = KitkatProfileViewGroup.class.getSimpleName();
    public KitkatProfileViewGroup(Context context) {
        super(context);

        setInit();
    }

    public KitkatProfileViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInit();
    }

    public KitkatProfileViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setInit();
    }

    private void setInit() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Logger.w(TAG,"setPadding-KitkatViewGroup");
            setPadding(getPaddingLeft(),
                        getPaddingTop(),
                        getPaddingRight(),
                        getPaddingBottom() + SystemBarUtils.getNavigationBarHeight(getContext()));

//            setBackgroundColor(Utils.resolveColor(getContext(), R.attr.colorPrimary, Color.BLACK));
        }
    }

}