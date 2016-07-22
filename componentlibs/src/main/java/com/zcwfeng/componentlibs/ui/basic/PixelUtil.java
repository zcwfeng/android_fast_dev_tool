package com.zcwfeng.componentlibs.ui.basic;

import android.util.TypedValue;

/**
 * Android dp to pixel manipulation
 */
public class PixelUtil {

  /**
   * Convert from DIP to PX
   */
  public static float toPixelFromDIP(float value) {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        DisplayMetricsHolder.getWindowDisplayMetrics());
  }

  /**
   * Convert from DIP to PX
   */
  public static float toPixelFromDIP(double value) {
    return toPixelFromDIP((float) value);
  }

  /**
   * Convert from SP to PX
   */
  public static float toPixelFromSP(float value) {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        value,
        DisplayMetricsHolder.getWindowDisplayMetrics());
  }

  /**
   * Convert from SP to PX
   */
  public static float toPixelFromSP(double value) {
    return toPixelFromSP((float) value);
  }

  /**
   * Convert from PX to DP
   */
  public static float toDIPFromPixel(float value) {
    return value / DisplayMetricsHolder.getWindowDisplayMetrics().density;
  }

}
