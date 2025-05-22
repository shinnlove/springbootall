/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import okhttp3.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * 使用ok http的http client。
 *
 * @author Tony Zhao
 * @version $Id: HttpClient.java, v 0.1 2025-03-03 15:00 Tony Zhao Exp $$
 */
public class HttpClientUtil {

    private OkHttpClient client;

    public HttpClientUtil(int connectTimeout, int readTimeout, int writeTimeout) {
        // 构建参数
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS);
        // build出来
        client = builder.build();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public HttpResult sendGet(String url) {
        // 直接url、空的请求体
        Request request = new Request.Builder().url(url).build();
        try {
            // 直接新建request执行
            Response response = client.newCall(request).execute();
            return new HttpResult(response.code(), null, response.body().string());
        } catch (Exception e) {
            return new HttpResult(e);
        }
    }

    public HttpResult sendPost(String url) {
        return sendPost(url, null);
    }

    public HttpResult sendPost(String url, Map<String, ?> params2) {
        // 表单类型提交
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (params2 != null) {
            Map<String, ?> sortedMap = new TreeMap<>(params2);
            for (Map.Entry<String, ?> entry : sortedMap.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    continue;
                }
                formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        // 提交url到post
        Request request = new Request.Builder().url(url).post(formBuilder.build()).build();
        try {
            // 执行请求体
            Response response = client.newCall(request).execute();
            return new HttpResult(response.code(), null, response.body().string());
        } catch (IOException e) {
            return new HttpResult(e);
        }
    }

    // 发送到url上、带上json参数
    public HttpResult sendPostJSON(String url, String jsonParams) {
        // 使用.get或.parse指定json类型
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonParams);

        // 请求具体url带上body参数
        Request request = new Request.Builder().url(url).post(body).build();

        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            // 返回response.body().string()、外边再包装了一下请求的code
            return new HttpResult(response.code(), null, response.body().string());
        } catch (IOException e) {
            return new HttpResult(e);
        }
    }

    @Data
    public static class HttpResult {

        public final int code;
        public final Throwable cause;
        public final String body;
        public final boolean success;

        private JSONObject jsonBody;

        HttpResult(Throwable cause) {
            this.code = -1;
            this.cause = cause;
            this.body = null;
            this.success = false;
        }

        HttpResult(int code, Throwable cause, String body) {
            this.code = code;
            this.cause = cause;
            this.body = body;
            this.success = (cause == null) && (code >= 200 && code < 300);
        }

        public JSONObject jsonBody() {
            if (jsonBody == null) {
                jsonBody = JSON.parseObject(body);
            }
            return jsonBody;
        }

        public String errorMessage() {
            if (success) {
                return null;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("Http client error [").append("code=").append(code);
            if (cause != null) {
                StringWriter errorWriter = new StringWriter();
                cause.printStackTrace(new PrintWriter(errorWriter));
                builder.append(" ,").append("exception=").append(errorWriter.toString());
            }
            builder.append("]");
            return builder.toString();
        }

    }

}