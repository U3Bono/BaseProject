package com.example.baseproject.iPresenter;

import com.example.baseproject.base.baseMVP.BaseIModel;
import com.example.baseproject.base.baseMVP.BaseIPresenter;
import com.example.baseproject.entity.UserDetailEntity;
import com.example.baseproject.iModel.UserDetailModel;
import com.example.baseproject.iView.activity.user.UserDetailActivity;

public class UserDetailPresenter extends BaseIPresenter<UserDetailActivity> {

    UserDetailModel userDetailModel;

    public UserDetailPresenter() {
        userDetailModel = new UserDetailModel();
    }

    public void getDetail(int user_id) {
        if (view == null | userDetailModel == null)
            return;
        userDetailModel.userDetail(user_id, new BaseIModel.ObjectBack<UserDetailEntity>() {
            @Override
            public void success(UserDetailEntity object) {
                view.success(object);
            }

            @Override
            public void error(String s) {
                view.error(s);
            }
        });
    }
}
