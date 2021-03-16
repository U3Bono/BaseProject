package com.example.baseproject.base;

/**
 * 时间：2019-11-16 17
 * 描述：Fragment基类
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baseproject.base.baseMVP.BaseIPresenter;
import com.example.baseproject.base.baseMVP.BaseIView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<T extends BaseIPresenter<V>, V extends BaseIView> extends Fragment {

    protected T iPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(getContentView(), null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegister();
        if (iPresenter != null) {
            iPresenter.detachView();
            iPresenter = null;
        }
    }

    protected void unRegister() {

    }

    public abstract int getContentView();

    protected abstract T setPresenter();

    protected abstract void initView();

}
