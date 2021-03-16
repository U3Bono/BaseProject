package com.example.baseproject.iView.activity.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baseproject.R;
import com.example.baseproject.router.RouterPath;
import com.example.baseproject.view.X5WebView;

@Route(path = RouterPath.commonWeb)
public class WebActivity extends AppCompatActivity {

    X5WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.baidu.com");
    }
}
