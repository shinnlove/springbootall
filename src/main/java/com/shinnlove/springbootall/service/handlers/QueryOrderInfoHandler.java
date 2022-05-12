/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;

/**
 * TODO: remove cm order po... use domain model instead!
 *  it's too dirty...
 *  need add a parameter field select hook.
 * 
 * @author Tony Zhao
 * @version $Id: QueryOrderInfoHandler.java, v 0.1 2022-03-06 12:20 PM Tony Zhao Exp $$
 */
@Service
public class QueryOrderInfoHandler implements ActionHandler<ReviseEntireInfo, CmOrderPo> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public CmOrderPo process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // 获取参数
        ReviseEntireInfo entireInfo = param(context);

        return new CmOrderPo();
    }

}