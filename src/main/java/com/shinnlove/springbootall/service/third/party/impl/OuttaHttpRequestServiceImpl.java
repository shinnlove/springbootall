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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @see OuttaHttpRequestService#requestOnce(String, String, Map, Class)
     */
    @Override
    public <T> ServiceResult<T> requestOnce(String url, String method, Map<String, Object> bizParams, Class<T> clazz) {
        HttpClientUtil.HttpResult result = null;

        logger.warn("请求前：准备请求三方接口：url={}, method={}, bizParams={}", url, method, bizParams);

        // 执行请求
        if (Biz3rdPartyConstant.METHOD_POST.equals(method)) {
            result = httpClientUtil.sendPost(url, bizParams);
        } else {
            result = httpClientUtil.sendGet(url);
        }

        logger.warn("请求后：请求三方接口返回结果：result.body={}", result.body);

        if (!result.success) {
            return ServiceResultFactory.fail(result.errorMessage());
        }

        if (StringUtils.isBlank(result.body)) {
            return ServiceResultFactory.fail("接口返回结果为空");
        }

        String responseJsonBody = result.body;

        logger.warn("三方结果请求体解析：{}", responseJsonBody);

        // do generic type conversion
        T data = null;
        try {
            data = JSON.parseObject(responseJsonBody, clazz);
        } catch (Exception e) {
            logger.error("解析接口返回结果失败", e);
            return ServiceResultFactory.fail("解析接口返回结果失败");
        }

        logger.warn("三方接口请求解析完毕，class类型:{}, 返回数据：{}", clazz, data);

        return ServiceResultFactory.success(data);
    }

}