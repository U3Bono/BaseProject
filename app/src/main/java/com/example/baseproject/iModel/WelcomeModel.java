package com.example.baseproject.iModel;

import com.example.baseproject.base.baseMVP.BaseIModel;
import com.example.baseproject.entity.WelcomeEntity;

public class WelcomeModel implements BaseIModel {

    public void welcome(ObjectBack<WelcomeEntity> objectBack) {

        WelcomeEntity welcomeEntity = new WelcomeEntity();
        welcomeEntity.setWelcomePic("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn2.image.apk.gfan.com%2Fasdf%2FPImages%2F2012%2F11%2F28%2F431990_241779f23-3d85-4430-ba73-aa92c1fe2626.jpg&refer=http%3A%2F%2Fcdn2.image.apk.gfan.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618321547&t=4251baf7da759d1fc579f20213b4671f");
        objectBack.success(welcomeEntity);
    }

}
