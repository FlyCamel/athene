package com.athene.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bing on 6/19/15.
 */
public class GlobalConfig {
    private static final String PREF_NAME = "global_pref";

    private static final String KEY_DEBUG_MODE = "debug_mode";

    public static String getStringValue(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        return pref.getString(key, "");
    }

    public static int getIntValue(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        return pref.getInt(key, -1);
    }

    public static long getLongValue(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        return pref.getLong(key, -1L);
    }

    public static void putStringValue(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putIntValue(Context context, String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putLongValue(Context context, String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void setDebugMode(Context context, int mode) {
        putIntValue(context, GlobalConfig.KEY_DEBUG_MODE, mode);
    }
}
