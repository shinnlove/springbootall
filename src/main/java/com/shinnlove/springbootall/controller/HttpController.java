/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.util.http.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: HttpController.java, v 0.1 2025-03-03 15:06 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/http")
public class HttpController {

    private static Logger logger = LoggerFactory.getLogger(HttpController.class);

    @Autowired
    private HttpClient httpClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello http request.";
    }

    @RequestMapping(value = "/ok_http_get", method = RequestMethod.GET)
    public String initHttpGet() {

        String url = "https://www.baidu.com";
        HttpClient.HttpResult result = httpClient.sendGet(url);

        logger.info("请求百度首页返回的状态码: {}, 响应体: {}", result.code, result.body);

        // 此处result.code是http的200 OK响应码

        if (result.success) {
            return result.body;
        } else {
            return result.errorMessage();
        }

    }


}