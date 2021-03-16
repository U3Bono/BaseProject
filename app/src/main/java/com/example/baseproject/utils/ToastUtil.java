package com.example.baseproject.utils;

/**
 * 弹窗提示
 */

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void errorToast(Context context, String msg) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void noticeToast(Context context, String msg){
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
