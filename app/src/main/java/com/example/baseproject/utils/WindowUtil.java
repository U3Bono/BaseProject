package com.example.baseproject.utils;

/**
 * 描述：窗口管理工具
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class WindowUtil {

    private Context context;

    private DisplayMetrics displayMetrics;
    private int w;
    private int h;

    private int windowHeight;
    private int windowWidth;

    public WindowUtil(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);         //窗口管理
        displayMetrics = new DisplayMetrics();                                   //屏幕解析
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    }

    public void setSize(View view, int h, int w) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();                //动态控制View位置
        view.measure(w, h);                               //测量Layout
        layoutParams.height = h;                                       //修改View高度
        layoutParams.width = w;                                         //修改View宽度
        view.setLayoutParams(layoutParams);
    }

    public int getWindowHeight() {
        return displayMetrics.heightPixels;
    }

    public int getWindowWidth() {
        return displayMetrics.widthPixels;
    }

}
