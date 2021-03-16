package com.example.baseproject.base;

/**
 * 时间：2019-11-16 17
 * 描述：Activity基类
 */

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.baseproject.base.baseMVP.BaseIPresenter;
import com.example.baseproject.base.baseMVP.BaseIView;

public abstract class BaseActivity<T extends BaseIPresenter<V>, V extends BaseIView> extends AppCompatActivity {

    protected T iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        init();
        register();
        initView();

        iPresenter = setPresenter();
        iPresenter.attachView((V) this);
    }

    protected void init() {

    }

    protected void register() {
        ARouter.getInstance().inject(this);                 //阿里路由注册
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).resumeRequests();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(this).pauseRequests();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
        if (iPresenter != null) {
            iPresenter.detachView();
            iPresenter = null;
        }
    }

    protected void unRegister() {

    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//字体非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//字体设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    public abstract int getContentView();

    protected abstract T setPresenter();

    protected abstract void initView();

}
