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
 * 初始化后台改价就通知UP主来确认价格。
 * 
 * @author Tony Zhao
 * @version $Id: InitNotifyUpper2ConfirmHandler.java, v 0.1 2022-03-10 1:05 PM Tony Zhao Exp $$
 */
@Service
public class InitNotifyUpper2ConfirmHandler implements ActionHandler<ReviseEntireInfo, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseEntireInfo entireInfo = param(context);
        CmOrderPo cmOrderPo = results(context, QueryOrderInfoHandler.class);

        return 1;
    }

}