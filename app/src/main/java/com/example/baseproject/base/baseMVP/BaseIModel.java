package com.example.baseproject.base.baseMVP;

/**
 * 时间：2021-1-23 18
 * 描述：Model接口
 */

import java.util.List;

public interface BaseIModel {

    interface ObjectBack<T> {
        void success(T object);

        void error(String s);
    }

    interface ListBack<T> {
        void success(List<T> list);

        void error(String s);
    }
}
