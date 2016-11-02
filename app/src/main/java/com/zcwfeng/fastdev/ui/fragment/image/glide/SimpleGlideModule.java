package com.zcwfeng.fastdev.ui.fragment.image.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

public class SimpleGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        /*
        You simply need to implement a GlideModule, like we've shown you above, and then call builder.
        setDecodeFormat(DecodeFormat.PREFER_ARGB_8888) with the correct enum value.
        */
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}