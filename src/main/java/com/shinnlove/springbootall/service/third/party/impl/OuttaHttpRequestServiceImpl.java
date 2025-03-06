/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.alibaba.fastjson.JSON;
import com.shinnlove.springbootall.service.third.party.OuttaHttpRequestService;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.dto.ServiceResult;
import com.shinnlove.springbootall.util.dto.ServiceResultFactory;
import com.shinnlove.springbootall.util.http.HttpClientUtil;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Tony Zhao
 * @version $Id: OuttaHttpRequestServiceImpl.java, v 0.1 2025-03-05 19:40 Tony Zhao Exp $$
 */
@Service
public class OuttaHttpRequestServiceImpl implements OuttaHttpRequestService {

    private static final Logger logger = LoggerFactory.getLogger(OuttaHttpRequestServiceImpl.class);

    /** http请求工具类 */
    @Resource
    private HttpClientUtil httpClientUtil;

    /**
     * @see OuttaHttpRequestService#requestOnce(String, String, Map, Type)
     */
    @Override
    public <T> ServiceResult<T> requestOnce(String url, String method, Map<String, Object> bizParams, Type type) {
        HttpClientUtil.HttpResult httpResult = null;

        logger.warn("请求前：准备请求三方接口：url={}, method={}, bizParams={}, type={}", url, method, bizParams, type);

        // 执行请求
        if (Biz3rdPartyConstant.METHOD_POST.equals(method)) {
            httpResult = httpClientUtil.sendPost(url, bizParams);
        } else {
            httpResult = httpClientUtil.sendGet(url);
        }

        logger.warn("请求后：请求三方接口返回结果：result.body={}", httpResult.body);

        if (!httpResult.success) {
            // http 不是200OK的case
            return ServiceResultFactory.fail(httpResult.getCode(), httpResult.errorMessage());
        }

        if (StringUtils.isBlank(httpResult.body)) {
            // http 200 OK 没有任何数据json
            return ServiceResultFactory.fail(-1, "接口返回结果为空");
        }

        String responseBodyJson = httpResult.body;

        logger.warn("三方接口HTTP 200 OK下，返回json体信息: {}", responseBodyJson);

        return typeConvert(responseBodyJson, type);
    }

    /**
     * HTTP 200 OK下，将json转换为带errorCode和errorMsg以及泛类型的业务领域模型。
     *
     * @param responseBodyJson
     * @param type
     * @param <T>                   使用 TypeReference 来捕获泛型类型信息
     * @return
     */
    private static <T> ServiceResult<T> typeConvert(String responseBodyJson, Type type) {
        YinGeResponse<T> response = null;
        try {
            // do generic type conversion
            response = JSON.parseObject(responseBodyJson, type);
        } catch (Exception e) {
            logger.error("解析接口返回结果失败", e);
            return ServiceResultFactory.fail(-1, "解析接口返回结果失败");
        }

        if (response.getCode() == 0) {
            // 接口业务结果ok，返回正确结果
            return ServiceResultFactory.success(response.getData());
        }

        // 返回错误结果
        return ServiceResultFactory.fail(response.getCode(), response.getError());
    }

}