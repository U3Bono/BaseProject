package com.example.baseproject.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baseproject.R;

public class GlideUtil {

    public static void commonIc(Activity activity, String url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)//加载前
                .fallback(R.mipmap.ic_launcher) //url为空
                .error(R.mipmap.ic_launcher);//加载错误
        Glide.with(activity).load(url).apply(options).into(view);
    }

    public static void commonIc(Activity activity, int icRes, ImageView view){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)//加载前
                .fallback(R.mipmap.ic_launcher) //url为空
                .error(R.mipmap.ic_launcher);//加载错误
        Glide.with(activity).load(icRes).apply(options).into(view);
    }

    public static void userIc(Activity activity, String url, ImageView view) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)//加载前
                .fallback(R.mipmap.ic_launcher) //url为空
                .error(R.mipmap.ic_launcher);//加载错误
        Glide.with(activity).load(url).apply(options).into(view);
    }

    public static void userIc(Activity activity, int icRes, ImageView view) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)//加载前
                .fallback(R.mipmap.ic_launcher) //url为空
                .error(R.mipmap.ic_launcher);//加载错误
        Glide.with(activity).load(icRes).into(view);
    }

}
