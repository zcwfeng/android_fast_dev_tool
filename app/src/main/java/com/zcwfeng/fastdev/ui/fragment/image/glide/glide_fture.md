[TOC]

## 简介

Glide — Advanced Loading
Glide — ListAdapter (ListView, GridView)
Glide — Placeholders & Fade Animations
Glide — Image Resizing & Scaling
Glide — Displaying Gifs & Videos
Glide — Caching Basics
Glide — Request Priorities
Glide — Thumbnails
Glide — Callbacks: SimpleTarget and ViewTarget for Custom View Classes
Glide — Loading Images into Notifications and AppWidgets
Glide — Exceptions: Debugging and Error Handling
Glide — Custom Transformations
Glide — Custom Animations with animate()
Glide — Integrating Networking Stacks
Glide — Customize Glide with Modules
Glide Module Example: Accepting Self-Signed HTTPS Certificates
Glide Module Example: Customize Caching
Glide Module Example: Optimizing By Loading Images In Custom Sizes
Glide — Dynamically Use Model Loaders
Glide — How to Rotate Images
Glide — Series Roundup

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

