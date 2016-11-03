package com.zcwfeng.fastdev.ui.fragment.image.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

public class CustomImageSizeGlideModule implements GlideModule {
    @Override public void applyOptions(Context context, GlideBuilder builder) {
        // nothing to do here

        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);

    }

    @Override public void registerComponents(Context context, Glide glide) {
        glide.register(CustomImageSizeModel.class, InputStream.class, new CustomImageSizeModelFactory());
    }
}