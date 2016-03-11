package com.zcwfeng.componentlibs.surport.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class ActivityHelper {

	public static final String KEY = "com.zcwfeng.android.activityhelp_key";
	
	private static Context mContext;

	private ActivityHelper() {
	}

	public static void config(Context context) {
		mContext = context;
	}

	/**
	 * 获取string，默认值为""
	 * 
	 * @param key
	 * @return
	 */
	public static String getShareData(String key) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}

	/**
	 * 获取string
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getShareData(String key, String defValue) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}

	/**
	 * 获取int
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getIntShareData(String key, int defValue) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}

	public static int getIntShareData(String key) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getInt(key, 0);
	}

	public static boolean getBooleanShareData(String key) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getBoolean(key, false);
	}

	public static boolean getBooleanShareData(String key, boolean defValue) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}

	public static void putShareData(String key, String value) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putString(key, value);
		et.commit();
	}

	public static void putIntShareData(String key, int value) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putInt(key, value);
		et.commit();
	}

	public static void putBooleanShareData(String key, boolean value) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putBoolean(key, value);
		et.commit();
	}

	public static void putSetShareData(String key, Set<String> value) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putStringSet(key, value);
		et.commit();
	}

	public static Set<String> getSetShareData(String key) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getStringSet(key, new HashSet<String>());
	}

}