package com.example.baseproject.router;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 时间：2019-11-16 17
 * 描述：路由跳转中心
 */
public class RouterCenter {

    public static void toCommonWelcome() {
        ARouter.getInstance().build(RouterPath.commonWelcome).navigation();
    }

    public static void toCommonHome() {
        ARouter.getInstance().build(RouterPath.commonHome).navigation();
    }

    public static void toCommonWeb(){
        ARouter.getInstance().build(RouterPath.commonWeb).navigation();
    }

    public static void toUserLogin() {
        ARouter.getInstance().build(RouterPath.userLogin).navigation();
    }

    public static void toUserDetail(int user_id){
        ARouter.getInstance().build(RouterPath.userDetail).withInt("user_id", user_id).navigation();
    }

}
