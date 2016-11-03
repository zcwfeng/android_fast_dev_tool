[TOC]

## 简介

<ol><li><a href="/tutorials/glide-getting-started">Glide — Getting Started</a></li><li><a href="/tutorials/glide-advanced-loading">Glide — Advanced Loading</a></li><li><a href="/tutorials/glide-listadapter-listview-gridview">Glide — ListAdapter (ListView, GridView)</a></li><li><a href="/tutorials/glide-placeholders-fade-animations">Glide — Placeholders &amp; Fade Animations</a></li><li><a href="/tutorials/glide-image-resizing-scaling">Glide — Image Resizing &amp; Scaling</a></li><li><a href="/tutorials/glide-displaying-gifs-and-videos">Glide — Displaying Gifs &amp; Videos</a></li><li><a href="/tutorials/glide-caching-basics">Glide — Caching Basics</a></li><li><a href="/tutorials/glide-request-priorities">Glide — Request Priorities</a></li><li><a href="/tutorials/glide-thumbnails">Glide — Thumbnails</a></li><li><a href="/tutorials/glide-callbacks-simpletarget-and-viewtarget-for-custom-view-classes">Glide — Callbacks: SimpleTarget and ViewTarget for Custom View Classes</a></li><li><a href="/tutorials/glide-loading-images-into-notifications-and-appwidgets">Glide — Loading Images into Notifications and AppWidgets</a></li><li><a href="/tutorials/glide-exceptions-debugging-and-error-handling">Glide — Exceptions: Debugging and Error Handling</a></li><li><a href="/tutorials/glide-custom-transformation">Glide — Custom Transformations</a></li><li><a href="/tutorials/glide-custom-animations-with-animate">Glide — Custom Animations with animate()</a></li><li><a href="/tutorials/glide-integrating-networking-stacks">Glide — Integrating Networking Stacks</a></li><li class="outline-item-active">Glide — Customize Glide with Modules</li><li><a href="/tutorials/glide-module-example-accepting-self-signed-https-certificates">Glide Module Example: Accepting Self-Signed HTTPS Certificates</a></li><li><a href="/tutorials/glide-module-example-customize-caching">Glide Module Example: Customize Caching</a></li><li><a href="/tutorials/glide-module-example-optimizing-by-loading-images-in-custom-sizes">Glide Module Example: Optimizing By Loading Images In Custom Sizes</a></li><li><a href="/tutorials/glide-dynamically-use-model-loaders">Glide —  Dynamically Use Model Loaders</a></li><li><a href="/tutorials/glide-how-to-rotate-images">Glide — How to Rotate Images</a></li><li><a href="/tutorials/glide-series-roundup-post">Glide —  Series Roundup</a></li><li><i class="fa fa-star"></i> Glide — Advanced ListViews With Images <i>(soon)</i></li><li><i class="fa fa-star"></i> Glide — App Release Preparation <i>(soon)</i></li></ol>

## Volley 配置网络请求Volley,Okhttp2 & Okhttp3

### Okhttp2
```
dependencies {  
    // your other dependencies
    // ...

    // Glide
    compile 'com.github.bumptech.glide:glide:3.7.0'

    // Glide's OkHttp2 Integration 
    compile 'com.github.bumptech.glide:okhttp-integration:1.4.0@aar'
    compile 'com.squareup.okhttp:okhttp:2.7.5'
}
```

### volley
```
dependencies {  
    // your other dependencies
    // ...

    // Glide
    compile 'com.github.bumptech.glide:glide:3.7.0'

    // Glide's Volley Integration 
    compile 'com.github.bumptech.glide:volley-integration:1.4.0@aar'
    compile 'com.mcxiaoke.volley:library:1.0.8'
}
```

### okhttp3

```
dependencies {  
    // your other dependencies
    // ...

    // Glide
    compile 'com.github.bumptech.glide:glide:3.7.0'

    // Glide's OkHttp3 Integration 
    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
}
```

## Glide 自定义模块

### 调用了GlideBuilder后的方法列表

* .setMemoryCache(MemoryCache memoryCache)
* .setBitmapPool(BitmapPool bitmapPool)
* .setDiskCache(DiskCache.Factory diskCacheFactory)
* .setDiskCacheService(ExecutorService service)
* .setResizeService(ExecutorService service)
* .setDecodeFormat(DecodeFormat decodeFormat)

### 图像的解码 

```
有两种主要方法解码图像Android:ARGB8888 RGB565。第一个为每个像素使用4个字节,后者为每个像素只有两个字节。
ARG8888有更高的图像质量的优势和能力来存储一个alpha通道。
而Picasso使用ARGB8888,Glide默认为小RGB565质量。

```

## Cache 缓存策略

* Memory cache needs to implement: MemoryCache
* Bitmap pool needs to implement BitmapPool
* Disk cache needs to implement: DiskCache

## Rotate 旋转图片相关

* android.graphics.Matrix
一般来说 旋转一个图片的写法如下:
```
Bitmap toTransform = ... // your bitmap source

Matrix matrix = new Matrix();  
matrix.postRotate(rotateRotationAngle);

Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);  

```