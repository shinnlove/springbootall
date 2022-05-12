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
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;

/**
 * 自助改价初始化消息发送(模型入参{@link ReviseEntireInfo}与{@link NotifyAdvertiser2ConfirmHandler}稍有不同)
 *
 * @author Tony Zhao
 * @version $Id: InitNotifyAdvertiser2ConfirmHandler.java, v 0.1 2022-01-25 3:39 PM Tony Zhao Exp $$
 */
@Service
public class InitNotifyAdvertiser2ConfirmHandler implements
                                                 ActionHandler<ReviseEntireInfo, Integer> {

    private static final Logger logger = LoggerFactory
        .getLogger(InitNotifyAdvertiser2ConfirmHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseEntireInfo entireInfo = param(context);
        CmOrderPo cmOrderPo = results(context, QueryOrderInfoHandler.class);

        return 1;
    }

}