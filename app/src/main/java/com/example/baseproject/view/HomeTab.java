package com.example.baseproject.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.baseproject.R;
import com.example.baseproject.iView.activity.user.UserDetailActivity;
import com.example.baseproject.utils.GlideUtil;


public class HomeTab extends LinearLayout {

    Context context;

    public HomeTab(Context context) {
        this(context, null);
    }

    public HomeTab(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        ViewGroup parent = (ViewGroup) getParent();
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = parent.getWidth();
        params.height = 80;
        setLayoutParams(params);
    }

    public void addTab(int logRes, String name, OnClickListener onClickListener) {
        if (context == null | name == null)
            return;
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.layout(10, 5, 10, 10);
        GlideUtil.commonIc((Activity) context, logRes, imageView);
        imageView.setAdjustViewBounds(true);
        imageView.setOnClickListener(onClickListener);

        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.layout(10, 5, 10, 10);
        textView.setText(name);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setOnClickListener(onClickListener);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOnClickListener(onClickListener);
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        linearLayout.setWeightSum(1);

        this.addView(linearLayout);
    }

}
