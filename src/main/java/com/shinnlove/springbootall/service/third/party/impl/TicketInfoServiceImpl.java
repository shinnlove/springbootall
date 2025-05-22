/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.third.party.impl;

import com.alibaba.fastjson.TypeReference;
import com.shinnlove.springbootall.service.third.party.OuttaHttpRequestService;
import com.shinnlove.springbootall.service.third.party.TicketInfoService;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.constants.MonthTicketBizConfig;
import com.shinnlove.springbootall.util.dto.ServiceResult;
import com.shinnlove.springbootall.util.third.party.SignatureUtil;
import com.shinnlove.springbootall.util.third.party.dto.CustomizeInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tony Zhao
 * @version $Id: TicketInfoServiceImpl.java, v 0.1 2025-03-05 19:35 Tony Zhao Exp $$
 */
@Service
public class TicketInfoServiceImpl implements TicketInfoService {

    /** 请求三方的http request 服务 */
    @Resource
    private OuttaHttpRequestService outtaHttpRequestService;

    /**
     * @see TicketInfoService#queryTicketInfoByCustomizeNo(String)
     */
    @Override
    public ServiceResult<CustomizeInfo> queryTicketInfoByCustomizeNo(String customizeNo) {

        // apollo config
        MonthTicketBizConfig bizConfig = new MonthTicketBizConfig();

        // 准备url endpoint
        String domain = bizConfig.getThirdPartyDomain();
        String endpoint = bizConfig.getCustomizeInfoValidateUrl();
        String url = domain + endpoint;

        // 业务必须的请求字段
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Biz3rdPartyConstant.CUSTOMIZE_NO, customizeNo);

        // 填充公共签名参数
        SignatureUtil.fillCommonSignature(paramsMap);

        // 定义返回类型、并请求印鸽接口
        Type type = new TypeReference<YinGeResult<CustomizeInfo>>() {}.getType();
        return outtaHttpRequestService.requestOnce(url, Biz3rdPartyConstant.METHOD_POST, paramsMap, type);
    }

}