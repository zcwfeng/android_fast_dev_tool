#AndroidFastDevTool

AndroidFastDevTool, A pure tools App based on Android and so on,to help developers use so fast and happy.

安卓快速开发工具，一个纯粹基于android平台的快速开发整理的工具和一些库，方便开发者，搜集好的技术和工具，提供学习和快速开发使用

本项目还在测试阶段，发现bug或有好的建议欢迎[issue](https://github.com/zcwfeng/android_fast_dev_tool/issues)、email(zcwfeng@gmail.com)、PR，如果感觉对你有帮助也欢迎点个star、fork，本项目仅做学习交流使用，相关API数据内容所有权归原作公司所有，请勿用于其他用途


> 转载和拉代码请注明出处


## 用到的知识点

* RenderScript [待整理]
* OKHttp [待整理]
* JNI NDK [待整理]
* C [待整理]
* RxJava [待整理]
* 开源播放器，media 方法 [待整理]
* EventBus 自定义 反射获取配置文件 [待整理]
* google 官方AD [待整理]
* google 官方MVP [待整理]
* RecycleView [待整理]
* android WebServer [待整理]
* NestedScrollView (自定义NestedScrollLayout) [待整理]
* 使用 Realm 做数据库存储
* 使用 GreenDao 做数据库存储 [待整理]
* 使用Glide做图片的处理和加载
* 使用Fresco做图片的处理和加载 [待整理]
* 使用Picasso做图片的处理和加载 [待整理]
* 使用x5WebView做阅览页，比原生WebView体验更佳
* TinkerPatch 热更新 [待整理]
* Dagger,Dagger2 [待加入]
* Weex [待加入]
* React [待加入]

> 待整理的代码

* utils 的整理 [待整理]
* 功能与代码结构的整理 [待整理]

> 一些比较好但是不好整理的Demo

* https://github.com/florent37/MaterialViewPager
* https://github.com/zcwfeng/android-image-utils
* [leakcanary] https://github.com/square/leakcanary
* https://github.com/zcwfeng/afinal
* https://github.com/EyalBira/loading-dots
* https://github.com/kikoso/android-stackblur
* https://github.com/googlesamples/android-SynchronizedNotifications.git
* https://github.com/jdamcd/android-crop
* https://github.com/chrisbanes/cheesesquare
* https://github.com/navasmdc/MaterialDesignLibrary
* https://github.com/yangfuhai/ASimpleCache
* https://github.com/zcwfeng/AndroidViewHover
* https://github.com/zcwfeng/huanxin_chatuidemo
* https://github.com/zcwfeng/ViewInspector
* https://github.com/zcwfeng/DraggerStudy
* https://github.com/xiongwei-git/AndroidVideoPlayer
* https://github.com/googlesamples/android-UniversalMusicPlayer
* https://github.com/zcwfeng/Android-PullToRefreshRecyclerView
* https://github.com/zcwfeng/AisenWeiBo
* https://github.com/zcwfeng/AndroidSourceStudyStudio
* https://github.com/facebook/fresco
* https://github.com/alibaba/AndFix
* https://github.com/dodola/HotFix
* https://github.com/dodola/RocooFix
* https://github.com/zcwfeng/vlc-android-demo
* https://github.com/zcwfeng/zcw_ijkplayer
* https://github.com/Bilibili/ijkplayer
* https://github.com/geftimov/android-pathview
* https://github.com/PhilJay/MPAndroidChart
* https://github.com/koral--/android-gif-drawable[GIF]
* https://github.com/koral--/android-gif-drawable-sample
* https://github.com/Cutta/GifView[GIF]
* https://github.com/bumptech/glide
* https://github.com/sbakhtiarov/gif-movie-view
* http://tinkerpatch.com/Docs/SDK[TinkerPatch 热更新]
* https://github.com/TinkerPatch/tinkerpatch-sample/blob/master/app/tinkerpatch.gradle
* https://github.com/Tencent/tinker/wiki/Tinker-%E6%8E%A5%E5%85%A5%E6%8C%87%E5%8D%97
* http://tinkerpatch.com/apps/detail/id/843

## Glide bug 通常解决方案

* Android使用Glide加载Gif.解决Glide加载Gif非常慢问题
Glide.with(MainActivity.this).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
为其添加缓存策略,其中缓存策略可以为:Source及None,None及为不缓存,Source缓存原型.如果为ALL和Result就不行

* https://github.com/bumptech/glide/issues/513

* https://github.com/bumptech/glide/issues/281

* https://github.com/bumptech/glide/issues/600


## android 一些特殊声明周期和问题研究

* 延时共享问题

```
http://www.androiddesignpatterns.com/2014/12/activity-fragment-transitions-in-android-lollipop-part1.html
http://www.androiddesignpatterns.com/2014/12/activity-fragment-content-transitions-in-depth-part2.html
http://www.androiddesignpatterns.com/2015/03/activity-postponed-shared-element-transitions-part3b.html
```


* Activity 声明周期 OnActivityReenter
ActivityTransition: OnActivityReenter: onPreDraw never called
##效果
![](https://github.com/zcwfeng/android_fast_dev_tool/raw/master/art/androidfastdev.gif)



##下载Demo

#[浏览应用网页](https://fir.im/2973)

或者

![扫描二维码](data:image/png;base64,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)



``` xml
MIT License

Copyright (c) 2016 zcwfeng(David)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
