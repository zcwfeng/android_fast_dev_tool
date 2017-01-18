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