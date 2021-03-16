package com.example.baseproject.http;

/**
 * 时间：2019-11-23 11
 * 描述：OkHttp请求辅助类
 */

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HttpHelper {

    private static HttpHelper httpHelper;
    private final OkHttpClient okHttpClient;

    private HttpHelper() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
    }

    public static HttpHelper getHttpHelper() {
        if (httpHelper == null) {
            synchronized (HttpHelper.class) {
                if (httpHelper == null) {
                    httpHelper = new HttpHelper();
                }
            }
        }
        return httpHelper;
    }

    public void doGet(String url, Map<String, Object> map, CallBack callBack) {
        String body = objToBody(map);
        final Request request = new Request.Builder()
                .url(url + body)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        Log.d(TAG, "url: " + url + body);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                callBack.fail(500, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse: " + body);
                        callBack.success(200, body);
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                        callBack.fail(200, String.valueOf(response.code()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void doPost(String url, Map<String, Object> map, CallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, map.get(key).toString());
        }
        final Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                callBack.fail(500, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse: " + body);
                        callBack.success(200, body);
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                        callBack.fail(200, String.valueOf(response.code()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void doPut(String url, Map<String, Object> map, CallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, map.get(key).toString());
        }
        final Request request = new Request.Builder()
                .url(url)
                .put(builder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                callBack.fail(500, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse: " + body);
                        callBack.success(200, body);
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                        callBack.fail(200, String.valueOf(response.code()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void doDelete(String url, Map<String, Object> map, CallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, map.get(key).toString());
        }
        final Request request = new Request.Builder()
                .url(url)
                .delete(builder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                callBack.fail(500, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse: " + body);
                        callBack.success(200, body);
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                        callBack.fail(200, String.valueOf(response.code()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public String objToBody(Map<String, Object> map) {
        if (map.isEmpty()) {
            return "";
        }
        String body = "?";
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (i != 0) {
                body += "&";
            }
            body += entry.getKey() + "=" + entry.getValue();
            i++;
        }

        return body;
    }

}
