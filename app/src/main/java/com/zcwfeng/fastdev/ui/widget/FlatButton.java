package com.zcwfeng.fastdev.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zcwfeng.componentlibs.surport.utils.Utils;
import com.zcwfeng.fastdev.R;

public class FlatButton extends TextView {

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

//    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setBackgroundCompat(Utils.resolveDrawable(context, R.attr.flat_text));

        final int materialBlue = Color.parseColor("#ff0000");
        int widgetColor = Utils.resolveColor(context, R.attr.colorAccent, materialBlue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            widgetColor = Utils.resolveColor(context, android.R.attr.colorAccent, widgetColor);
        }
        setTextColor(widgetColor);
    }

    private void setBackgroundCompat(Drawable d) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            setBackgroundDrawable(d);
        } else {
            setBackground(d);
        }
    }

}