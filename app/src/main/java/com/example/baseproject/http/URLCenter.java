package com.example.baseproject.http;

public interface URLCenter {

    String base_url = "http://192.168.1.101:8080/BaseProjectServer";

    String user_login = base_url + "/user/login";
    String user_register = base_url + "/user/register";
    String user_detail = base_url + "/user/detail";
    String user_modify = base_url + "/user/modify";
    String user_cancel = base_url + "/user/cancel";

}
