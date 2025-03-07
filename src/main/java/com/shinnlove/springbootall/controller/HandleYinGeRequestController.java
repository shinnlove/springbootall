/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.third.party.YinGeResultFactory;
import com.shinnlove.springbootall.util.third.party.YinGeSignature;
import com.shinnlove.springbootall.util.third.party.YinGeValidateUtil;
import com.shinnlove.springbootall.util.third.party.dto.YinGeOrderInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tony Zhao
 * @version $Id: HandleYinGeRequestController.java, v 0.1 2025-03-06 21:40 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/yuewen")
public class HandleYinGeRequestController {

    private static Logger logger = LoggerFactory.getLogger(HandleYinGeRequestController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello http request.";
    }

    @RequestMapping(value = "/test_soft_handle", method = RequestMethod.POST)
    public YinGeResult<YinGeOrderInfo> testHandleYinGeRequest(@RequestBody MultiValueMap<String, Object> formData) {
        logger.info("请求参数：formData={}", formData);

        try {
            // 签名参数字段存在性校验
            YinGeSignature yinGeSignature = YinGeValidateUtil.validateSignatureRequiredFields(formData);

            // 签名验签校验
            YinGeValidateUtil.validateYinGeSignature(formData, yinGeSignature);

            // 必要业务字段
            String outTradeNo = YinGeValidateUtil.validateAndExtract(formData, Biz3rdPartyConstant.OUT_TRADE_NO, String.class);

            logger.info("Received outTradeNo: " + outTradeNo);

            // 处理逻辑


            return YinGeResultFactory.success(new YinGeOrderInfo());

        } catch (Exception e) {
            return YinGeResultFactory.fail(-1, e.getMessage());
        }
    }

//    @Deprecated
//    @RequestMapping(value = "/test_handle", method = RequestMethod.POST)
//    public Integer testHandleYinGeRequest(@RequestParam("timestamp") int timestamp,
//                                          @RequestParam("resellerFlag") String resellerFlag,
//                                          @RequestParam("version") int version,
//                                          @RequestParam("signType") int signType,
//                                          @RequestParam("sign") String sign,
//                                          @RequestParam("outTradeNo") String outTradeNo) {
//        logger.info("请求参数：timestamp={}", timestamp);
//        logger.info("请求参数：resellerFlag={}", resellerFlag);
//        logger.info("请求参数：version={}", version);
//        logger.info("请求参数：signType={}", signType);
//        logger.info("请求参数：sign={}", sign);
//        logger.info("请求参数：outTradeNo={}", outTradeNo);
//        return 1;
//    }

}