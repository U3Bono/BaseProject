package com.example.baseproject.base.baseMVP;

/**
 * 时间：2021-1-23 18
 * 描述：View接口
 */
public interface BaseIView {

    void loadData();                        //加载数据

    void success(Object obj);

    void error(String msg);                 //显示错误

}
