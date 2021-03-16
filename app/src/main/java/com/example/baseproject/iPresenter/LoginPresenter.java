package com.example.baseproject.iPresenter;

import com.example.baseproject.base.baseMVP.BaseIModel;
import com.example.baseproject.base.baseMVP.BaseIPresenter;
import com.example.baseproject.entity.LoginEntity;
import com.example.baseproject.iModel.LoginModel;
import com.example.baseproject.iView.activity.user.LoginActivity;

public class LoginPresenter extends BaseIPresenter<LoginActivity> {

    LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void doLogin(LoginEntity loginEntity, int opType) {
        if (view == null || loginModel == null)
            return;
        loginModel.login(loginEntity, opType,new BaseIModel.ObjectBack<Integer>() {
            @Override
            public void success(Integer object) {
                view.success(object);
            }

            @Override
            public void error(String s) {
                view.error(s);
            }
        });
    }

}
