package com.example.baseproject.http;

public interface CallBack {

    void success(int type, String result);

    void fail(int type, String result);

}
