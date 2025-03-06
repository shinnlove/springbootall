/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinnlove.springbootall.service.third.party.OuttaHttpRequestService;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.constants.MonthTicketBizConfig;
import com.shinnlove.springbootall.util.dto.ServiceResult;
import com.shinnlove.springbootall.util.third.party.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Tony Zhao
 * @version $Id: HttpController.java, v 0.1 2025-03-03 15:06 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/http")
public class HttpController {

    private static Logger logger = LoggerFactory.getLogger(HttpController.class);

    @Autowired
    private OuttaHttpRequestService outtaHttpRequestService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello http request.";
    }

    @RequestMapping(value = "/test_binding", method = RequestMethod.GET)
    public Integer testBindingOrderNoWithCustomizeNo() {

        MonthTicketBizConfig bizConfig = new MonthTicketBizConfig();

//        String domain = bizConfig.getLocalhostDebugDomain();
        String domain = bizConfig.getThirdPartyDomain();
        String endpoint = bizConfig.getThirdPartyOrderPaidNotifyEndpoint();
        String url = domain + endpoint;

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Biz3rdPartyConstant.CUSTOMIZE_NO, "EGznO7vX");
        paramsMap.put(Biz3rdPartyConstant.OUT_TRADE_NO, "202503071236688");

        SignatureUtil.fillCommonSignature(paramsMap);

        Map<String, Object> printMap = new TreeMap<>(paramsMap);
        printMap.remove(Biz3rdPartyConstant.SECRET);

        logger.warn("控制器：请求三方接口json参数：{}", mapToJsonString(printMap));

        ServiceResult<Object> result = outtaHttpRequestService.requestOnce(url, Biz3rdPartyConstant.METHOD_POST, paramsMap, Object.class);

        if (result.isSuccess()) {
            logger.warn("控制器：请求三方接口返回结果：{}", result.getData());
        } else {
            logger.warn("控制器：绑定订单号和自定义单号失败, result={}", result);
        }

        return result.isSuccess() ? 1 : 0;
    }

    public static String mapToJsonString(Map<String, Object> map) {
        // 创建一个 TreeMap，保证键的字典序
        Map<String, Object> treeMap = new TreeMap<>(map);

        String json = "";
        // 使用 Jackson 将 Map 转换为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(treeMap);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}