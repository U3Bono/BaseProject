package com.example.baseproject.iPresenter;

import com.example.baseproject.base.baseMVP.BaseIModel;
import com.example.baseproject.base.baseMVP.BaseIPresenter;
import com.example.baseproject.entity.WelcomeEntity;
import com.example.baseproject.iModel.WelcomeModel;
import com.example.baseproject.iView.activity.common.WelcomeActivity;


public class WelcomePresenter extends BaseIPresenter<WelcomeActivity> {

    WelcomeModel welcomeModel;

    public WelcomePresenter() {
        welcomeModel = new WelcomeModel();
    }

    public void doWelcome() {
        if (view == null || welcomeModel == null)
            return;
        welcomeModel.welcome(new BaseIModel.ObjectBack<WelcomeEntity>() {
            @Override
            public void success(WelcomeEntity object) {
                view.success(object);
            }

            @Override
            public void error(String s) {
                view.error(s);
            }
        });
    }
}
