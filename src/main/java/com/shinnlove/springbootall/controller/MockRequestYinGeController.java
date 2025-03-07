/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.alibaba.fastjson.TypeReference;
import com.shinnlove.springbootall.service.third.party.OuttaHttpRequestService;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.constants.MonthTicketBizConfig;
import com.shinnlove.springbootall.util.dto.ServiceResult;
import com.shinnlove.springbootall.util.third.party.SignatureUtil;
import com.shinnlove.springbootall.util.third.party.dto.CustomizeInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Tony Zhao
 * @version $Id: MockRequestYinGeController.java, v 0.1 2025-03-03 15:06 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/http")
public class MockRequestYinGeController {

    private static Logger logger = LoggerFactory.getLogger(MockRequestYinGeController.class);

    @Autowired
    private OuttaHttpRequestService outtaHttpRequestService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello http request.";
    }

    @RequestMapping(value = "/test_query_mock", method = RequestMethod.GET)
    public String testQueryMockInfo(@RequestParam("customizeNo") String customizeNo) {

        MonthTicketBizConfig bizConfig = new MonthTicketBizConfig();

//        String domain = bizConfig.getLocalhostDebugDomain();

        String domain = bizConfig.getThirdPartySandBoxDomain();
        String endpoint = bizConfig.getCustomizeInfoValidateUrl();
        String url = domain + endpoint;

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Biz3rdPartyConstant.CUSTOMIZE_NO, customizeNo);

        SignatureUtil.fillCommonSignature(paramsMap);

        //        Map<String, Object> printMap = new TreeMap<>(paramsMap);
//        printMap.remove(Biz3rdPartyConstant.SIGN);
//
//        logger.warn("控制器：请求三方接口json参数：{}", SignatureUtil.mapToJsonString(printMap));

        // 构建具体的返回类型
        Type type = new TypeReference<YinGeResult<CustomizeInfo>>() {}.getType();

        // do request
        ServiceResult<CustomizeInfo> result = outtaHttpRequestService.requestOnce(url, Biz3rdPartyConstant.METHOD_POST, paramsMap, type);

        return result.getMessage();

//        if (result.isSuccess()) {
//            logger.warn("控制器：请求三方接口返回结果：{}", result.getData());
//        } else {
//            logger.warn("控制器：绑定订单号和自定义单号失败, result={}", result);
//        }
//
//        return result.isSuccess() ? 1 : 0;
    }

    @RequestMapping(value = "/test_binding", method = RequestMethod.GET)
    public Integer testBindingOrderNoWithCustomizeNo() {

        MonthTicketBizConfig bizConfig = new MonthTicketBizConfig();

//        String domain = bizConfig.getLocalhostDebugDomain();
        String domain = bizConfig.getThirdPartyDomain();
        String endpoint = bizConfig.getThirdPartyOrderPaidNotifyEndpoint();
        String url = domain + endpoint;

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Biz3rdPartyConstant.CUSTOMIZE_NO, "EGznO2yl");
        paramsMap.put(Biz3rdPartyConstant.OUT_TRADE_NO, "202503071236689");

        SignatureUtil.fillCommonSignature(paramsMap);

//        Map<String, Object> printMap = new TreeMap<>(paramsMap);
//        printMap.remove(Biz3rdPartyConstant.SIGN);
//
//        logger.warn("控制器：请求三方接口json参数：{}", SignatureUtil.mapToJsonString(printMap));

        // 构建具体的返回类型
        Type type = new TypeReference<YinGeResult<List<Object>>>() {}.getType();

        // do request
        ServiceResult<List<Object>> result = outtaHttpRequestService.requestOnce(url, Biz3rdPartyConstant.METHOD_POST, paramsMap, type);

        if (result.isSuccess()) {
            logger.warn("控制器：请求三方接口返回结果：{}", result.getData());
        } else {
            logger.warn("控制器：绑定订单号和自定义单号失败, result={}", result);
        }

        return result.isSuccess() ? 1 : 0;
    }

}