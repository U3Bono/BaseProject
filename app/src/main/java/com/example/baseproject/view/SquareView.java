package com.example.baseproject.view;

/**
 * 正方形图
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class SquareView extends ImageView {

    public SquareView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
