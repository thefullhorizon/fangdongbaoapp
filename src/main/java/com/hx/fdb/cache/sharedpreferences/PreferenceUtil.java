package com.hx.fdb.cache.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hx.fdb.app.HSApplication;

/**
 * Created by yanxin on 17/3/21.
 */

public class PreferenceUtil {

    private static final String COMMON_KEY = "COMMON_KEY";
    private static final String USERINFO_KEY = "USERINFO_KEY";

    public static String getString(String key) {
        if(TextUtils.isEmpty(key)) {
            return null;
        }
        if(HSApplication.getApplication() == null) {
            return null;
        }

        SharedPreferences sp = HSApplication.getApplication().getSharedPreferences(COMMON_KEY, Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static boolean getBoolean(String key) {
        if(TextUtils.isEmpty(key)) {
            return false;
        }
        if(HSApplication.getApplication() == null) {
            return false;
        }

        SharedPreferences sp = HSApplication.getApplication().getSharedPreferences(COMMON_KEY, Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    public static void putString(String key,String val) {
        if(TextUtils.isEmpty(key)) {
            return;
        }
        if(HSApplication.getApplication() == null) {
            return;
        }

        SharedPreferences sp = HSApplication.getApplication().getSharedPreferences(COMMON_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,val);
        editor.apply();
    }

    public static void putBoolean(String key,boolean val) {
        if(TextUtils.isEmpty(key)) {
            return;
        }
        if(HSApplication.getApplication() == null) {
            return;
        }

        SharedPreferences sp = HSApplication.getApplication().getSharedPreferences(COMMON_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,val);
        editor.apply();
    }

    public static String getUser(Context context,String key) {
        if(TextUtils.isEmpty(key)) {
            return null;
        }

        if(context == null) {
            context = HSApplication.getApplication();
        }
        if(context == null) {
            return null;
        }

        SharedPreferences sp = context.getSharedPreferences(USERINFO_KEY, Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void putUser(String key,String val) {
        if(TextUtils.isEmpty(key)) {
            return;
        }
        if(HSApplication.getApplication() == null) {
            return;
        }

        SharedPreferences sp = HSApplication.getApplication().getSharedPreferences(USERINFO_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,val);
        editor.commit();
    }

}
