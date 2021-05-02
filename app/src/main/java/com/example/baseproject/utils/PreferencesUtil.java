package com.example.baseproject.utils;

/**
 * 序列化工具SharedPreference
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PreferencesUtil {

    private static final String USER_INFO = "user_info";

    /**
     * int序列化
     */
    public static void setIntPreference(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntPreference(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * boolean序列化
     */
    public static void setBooleanPreferences(Context context, String key, Boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBooleanPreference(Context context, String key, Boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * string序列化
     */
    public static void setStringPreferences(Context context, String key, String value) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
            Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getStringPreference(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * long序列化
     */
    public static void setLongPreference(Context context, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLongPreference(Context context, String key, long defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * Object序列化
     */
    public static void putBean(Context context, String key, Object obj) {
        if (obj instanceof Serializable) {  // obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(byteArrayOutputStream.toByteArray(),
                        0));
                Editor editor = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit();
                editor.putString(key, string64).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("the obj must implement Serializable");
        }
    }

    public static Object getBean(Context context, String key) {
        Object obj = null;
        try {
            String base64 = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
