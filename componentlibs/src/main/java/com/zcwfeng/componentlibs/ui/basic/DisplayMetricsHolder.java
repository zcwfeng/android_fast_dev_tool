package com.zcwfeng.componentlibs.ui.basic;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import junit.framework.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DisplayMetricsHolder {

  private static
  DisplayMetrics sWindowDisplayMetrics;
  private static DisplayMetrics sScreenDisplayMetrics;

  /**
   * @deprecated Use {@link #setScreenDisplayMetrics(DisplayMetrics)} instead. See comment above as
   *    to why this is not correct to use.
   */
  public static void setWindowDisplayMetrics(DisplayMetrics displayMetrics) {
    sWindowDisplayMetrics = displayMetrics;
  }
  
  @TargetApi(Build.VERSION_CODES.KITKAT)
  public static void initDisplayMetricsIfNotInitialized(Context context) {
    if (DisplayMetricsHolder.getScreenDisplayMetrics() != null) {
      return;
    }
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    DisplayMetricsHolder.setWindowDisplayMetrics(displayMetrics);

    DisplayMetrics screenDisplayMetrics = new DisplayMetrics();
    screenDisplayMetrics.setTo(displayMetrics);
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Assert.assertNotNull(
            "WindowManager is null!", wm
        );
    Display display = wm.getDefaultDisplay();

    // Get the real display metrics if we are using API level 17 or higher.
    // The real metrics include system decor elements (e.g. soft menu bar).
    //
    // See: http://developer.android.com/reference/android/view/Display.html#getRealMetrics(android.util.DisplayMetrics)
    if (Build.VERSION.SDK_INT >= 17) {
      display.getRealMetrics(screenDisplayMetrics);
    } else {
      // For 14 <= API level <= 16, we need to invoke getRawHeight and getRawWidth to get the real dimensions.
      // Since react-native only supports API level 16+ we don't have to worry about other cases.
      //
      // Reflection exceptions are rethrown at runtime.
      //
      // See: http://stackoverflow.com/questions/14341041/how-to-get-real-screen-height-and-width/23861333#23861333
      try {
        Method mGetRawH = Display.class.getMethod("getRawHeight");
        Method mGetRawW = Display.class.getMethod("getRawWidth");
        screenDisplayMetrics.widthPixels = (Integer) mGetRawW.invoke(display);
        screenDisplayMetrics.heightPixels = (Integer) mGetRawH.invoke(display);
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        throw new RuntimeException("Error getting real dimensions for API level < 17", e);
      }
    }
    DisplayMetricsHolder.setScreenDisplayMetrics(screenDisplayMetrics);
  }

  /**
   * @deprecated Use {@link #getScreenDisplayMetrics()} instead. See comment above as to why this
   *    is not correct to use.
   */
  @Deprecated
  public static DisplayMetrics getWindowDisplayMetrics() {
    return sWindowDisplayMetrics;
  }

  public static void setScreenDisplayMetrics(DisplayMetrics screenDisplayMetrics) {
    sScreenDisplayMetrics = screenDisplayMetrics;
  }

  public static DisplayMetrics getScreenDisplayMetrics() {
    return sScreenDisplayMetrics;
  }
}
