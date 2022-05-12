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
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * 审核同意通知客户确认。
 * 
 * @author Tony Zhao
 * @version $Id: NotifyAdvertiser2ConfirmHandler.java, v 0.1 2022-03-10 12:32 PM Tony Zhao Exp $$
 */
@Service
public class NotifyAdvertiser2ConfirmHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        //订单查询
        CmOrderPo cmOrderPo = results(context, QueryOrderInfo2Handler.class);
        ReviseRegister reviseRegister = results(context, QueryReviseInfoHandler.class);

        int reviseType = reviseRegister.getReviseType();

        return 1;
    }

}