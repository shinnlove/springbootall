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
import com.shinnlove.springbootall.service.enums.ReviseType;
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * 通知UP主客户同意消息处理器。
 * 
 * @author Tony Zhao
 * @version $Id: notifyAdvertiserConfirmedHandler.java, v 0.1 2022-01-25 3:39 PM Tony Zhao Exp $$
 */
@Service
public class NotifyAdvertiserConfirmedHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseRegister reviseRegister = results(context, QueryReviseInfoHandler.class);
        CmOrderPo cmOrderPo = results(context, QueryOrderInfo2Handler.class);

        if (reviseRegister.getReviseType().equals(ReviseType.SELF_SERVICE.getCode())) {
            //自助改价场景，客户接受了改价，改价状态变为“客户已接受"时,发送给个人UP主/MCN机构
        }

        return 1;
    }

}