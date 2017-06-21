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
* ReactNative
* ConstraintLayout的简单实用例子
* 代码检测lint

> 待整理的代码

* utils 的整理 [待整理]
* 功能与代码结构的整理 [待整理]

> 一些比较好但是不好整理的Demo

* https://github.com/florent37/MaterialViewPager
* https://github.com/zcwfeng/android-image-utils
* [leakcanary](https://github.com/square/leakcanary)
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
* [GIF android-gif-drawable](https://github.com/koral--/android-gif-drawable)
* https://github.com/koral--/android-gif-drawable-sample
* [GIFView](https://github.com/Cutta/GifView)
* https://github.com/bumptech/glide
* https://github.com/sbakhtiarov/gif-movie-view
* [TinkerPatch 热更新](http://tinkerpatch.com/Docs/SDK)
* https://github.com/TinkerPatch/tinkerpatch-sample/blob/master/app/tinkerpatch.gradle
* https://github.com/Tencent/tinker/wiki/Tinker-%E6%8E%A5%E5%85%A5%E6%8C%87%E5%8D%97
* http://tinkerpatch.com/apps/detail/id/843
* [ReLinker 降低加载so的出错的几率](https://github.com/KeepSafe/ReLinker) 
* Flurry 和 Fabric 添加。具体网上可查，科学上网

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


## ConstrantLayout 依赖无法使用解决

```
a. 点击Tools>Android>SDK Manager
b. 点击SDK Tools标签
c.选择show pack details，找到support repository->constraintlayout for android下面可以看到现在你的系统中安装了
    constraint-layout:1.0.0-alpha4没有
d.选择你想安装的版本，点apply,会出现下载安装有界面。

```

## 检测代码 lint

```
.gradlew lint

```

## 检测代码 findBugs

详见app/gradle

## 检测代码 QAPlugin-PMD

##下载Demo

## 构建本应用说明

* ReactNative构建

首先当然要了解你要植入的React Native组件。
在Android项目根目录中使用npm来安装react-native ，这样同时会创建一个node_modules/的目录。
创建js文件，编写React Native组件的js代码。
在build.gradle文件中添加com.facebook.react:react-native:+，以及一个指向node_nodules/目录中的react-native预编译库的maven路径。
创建一个React Native专属的Activity，在其中再创建ReactRootView。
启动React Native的Packager服务，运行应用。
根据需要添加更多React Native的组件。
在真机上运行、调试。
打包。
> 在项目的根目录中运行：

```
$ npm init
$ npm install --save react react-native
$ curl -o .flowconfig https://raw.githubusercontent.com/facebook/react-native/master/.flowconfig
```

> 在package.json下面，scripts:标签内添加

```
"start": "node node_modules/react-native/local-cli/cli.js start"
```

> 编写 index.android.js，和你的RN的java代码入口保持一致

> 修改app 中的build.gradle

如果不行，可以修改+，替换成你指定的版本

```
 dependencies {
     ...
     compile "com.facebook.react:react-native:+" // From node_modules.
 }
```

> 在项目下的 build.gradle 添加

```
allprojects {
    repositories {
        ...
        maven {
            // All of React Native (JS, Android binaries) is installed from npm
            url "$rootDir/../node_modules/react-native/android"
        }
    }
    ...
}
```

确保你的路径正确，不会再Android Studio编译后出现类似 “Failed to resolve: com.facebook.react:react-native:0.x.x" 的错误

> 在AndroidManifest.xml检查权限，配置debug 配置activity

```
<uses-permission android:name="android.permission.INTERNET" />

<activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />
```

> 原生代码编写

两种方式，带研究。。。(涉及到了android的版本问题，这里比较让人头疼，包括很多人也是纠结这个问题)

```
public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler

public class MyReactActivity extends ReactActivity
```

注意BuildConfig类导入的时候使用的是你app里面的，不是react的

注册Activity

```
 <activity
   android:name=".MyReactActivity"
   android:label="@string/app_name"
   android:theme="@style/Theme.AppCompat.Light.NoActionBar">
 </activity>
```

> 运行应用首先需要启动开发服务器（Packager）。你只需在项目根目录中执行以下命令即可：

```
npm start
```

android 用 AS或者命令./gradlew installDebug安装都行

在此之前 确保安装了watchman，可以保持监听通信

> 在Android Studio中打包

```
react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output android/com/your-company-name/app-package-name/src/main/assets/index.android.bundle --assets-dest android/com/your-company-name/app-package-name/src/main/res/
```
本项目，可以测试打包

```
react-native bundle --platform android --dev false --entry-file index.android.js \
  --bundle-output app/src/main/assets/index.android.bundle \
  --assets-dest app/src/main/res/
```
#[浏览应用网页](https://fir.im/2973)

或者

![扫描二维码](https://github.com/zcwfeng/android_fast_dev_tool/raw/master/art/下载.png)

# TODO
```
图片
https://github.com/glassLake/CropUtils/blob/master/CropUtils.java
https://github.com/Yalantis/uCrop
https://github.com/glassLake/PhotoPicker

上传
https://github.com/Curzibn/Luban
https://github.com/glassLake/ServerPicUtils

https://github.com/Commit451/NativeStackBlur
https://github.com/wasabeef/fresco-processors
```

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
