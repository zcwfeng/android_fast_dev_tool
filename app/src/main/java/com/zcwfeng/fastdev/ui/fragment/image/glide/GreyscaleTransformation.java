package com.zcwfeng.fastdev.ui.fragment.image.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by zcw on 2016/10/31.
 */

public class GreyscaleTransformation
        extends BitmapTransformation {
    public GreyscaleTransformation(Context context) {
        super(context);
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return toTransform;
    }

    @Override
    public String getId() {
        return "GreyscaleTransformation";
    }
}
