package com.example.baseproject.iModel;

import com.example.baseproject.base.baseMVP.BaseIModel;
import com.example.baseproject.entity.LoginEntity;
import com.example.baseproject.http.CallBack;
import com.example.baseproject.http.HttpHelper;
import com.example.baseproject.http.URLCenter;
import com.example.baseproject.utils.FieldUtil;
import com.example.baseproject.utils.JsonFactory;

import java.util.Map;

public class LoginModel implements BaseIModel {

    public void login(LoginEntity loginEntity, int opType, ObjectBack<Integer> objectBack) {
        if (loginEntity.getUser_phone() == null | loginEntity.getUser_password() == null) {
            objectBack.error("failed");
            return;
        }
        String url = opType == 1 ? URLCenter.user_login : URLCenter.user_register;
        Map<String, Object> map = FieldUtil.getEntity(loginEntity);
        HttpHelper.getHttpHelper().doPost(url, map, new CallBack() {
            @Override
            public void success(int type, String result) {
                int user_id = Integer.parseInt(JsonFactory.getValue(result, "user_id"));
                objectBack.success(user_id);
            }

            @Override
            public void fail(int type, String result) {
                objectBack.error(result);
            }
        });
    }

}
