package com.example.baseproject.iModel;

import com.example.baseproject.base.baseMVP.BaseIModel;
import com.example.baseproject.entity.UserDetailEntity;
import com.example.baseproject.http.CallBack;
import com.example.baseproject.http.HttpHelper;
import com.example.baseproject.http.URLCenter;
import com.example.baseproject.utils.JsonFactory;

import java.util.HashMap;
import java.util.Map;

public class UserDetailModel implements BaseIModel {

    public void userDetail(int user_id, ObjectBack<UserDetailEntity> objectBack) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        HttpHelper.getHttpHelper().doGet(URLCenter.user_detail, map, new CallBack() {
            @Override
            public void success(int type, String result) {
                UserDetailEntity userDetailEntity = (UserDetailEntity) JsonFactory.jsonToObject(result, UserDetailEntity.class);
                objectBack.success(userDetailEntity);
            }

            @Override
            public void fail(int type, String result) {
                objectBack.error(result);
            }
        });

    }
}
