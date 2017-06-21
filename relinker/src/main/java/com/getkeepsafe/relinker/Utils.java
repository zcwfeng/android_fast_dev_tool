package com.getkeepsafe.relinker;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * *************************************************************************
 *
 * @版权所有: 北京云图微动科技有限公司 (C) 2016
 * @创建人: danhantao
 * @创建时间: danhantao(2016-12-02 13:10)
 * @Email: danhantao@yeah.net <p> 描述:com.getkeepsafe.relinker-Utils <p> <p>
 * *************************************************************************
 */
public class Utils {

  public static String getCurrentApkAbi(Context ctx) {
    if (ctx == null) return null;
    PackageManager packageManager = ctx.getPackageManager();
    int versionCode = 0;
    try {
      PackageInfo packInfo =
          packageManager.getPackageInfo(ctx.getApplicationContext().getPackageName(), 0);
      versionCode = packInfo.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    String abi = null;
    if (versionCode != 0) {
      switch (versionCode % 10) {
        case 0:
        case 1:
          abi = "armeabi";
          break;
        case 2:
          abi = "armeabi-v7a";
          break;
        case 3:
          abi = "arm64-v8a";
          break;
        case 8:
          abi = "x86";
          break;
        case 9:
          abi = "x86_64";
          break;
      }
    }
    return abi;
  }
}
