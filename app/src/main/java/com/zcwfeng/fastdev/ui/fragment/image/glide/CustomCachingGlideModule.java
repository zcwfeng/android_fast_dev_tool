package com.zcwfeng.fastdev.ui.fragment.image.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

public class CustomCachingGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        // 第一种 如果你认为你的应用程序需要缓存比滑翔的默认值大20%,计算使用上面的变量

//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
//        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
//
//        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
//        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
//
//        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
//        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));


        // 第二种 Customize Disk Cache

        // set size & external vs. internal
        int cacheSize100MegaBytes = 104857600;

        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
        );

        //builder.setDiskCache(
        //new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // nothing to do here
    }


    // use demo
    /*
    // or any other path
    String downloadDirectoryPath = Environment.getDownloadCacheDirectory().getPath();

    builder.setDiskCache(
            new DiskLruCacheFactory( downloadDirectoryPath, cacheSize100MegaBytes )
    );

    // In case you want to specify a cache sub folder (i.e. "glidecache"):
    //builder.setDiskCache(
    //    new DiskLruCacheFactory( downloadDirectoryPath, "glidecache", cacheSize100MegaBytes )
    //);
    */
}