package com.example.baseproject.base.baseMVP;

/**
 * 时间：2021-1-23 18
 * 描述：Presenter接口
 */
public class BaseIPresenter<T extends BaseIView> {

    protected T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

}
