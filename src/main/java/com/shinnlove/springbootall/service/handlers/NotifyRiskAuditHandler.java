/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.process.chain.ActionChain;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.service.biz.model.AuditResult;
import com.shinnlove.springbootall.service.biz.model.ReviseInfo;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: NotifyRiskAuditHandler.java, v 0.1 2022-01-25 3:36 PM Tony Zhao Exp $$
 */
@Service
public class NotifyRiskAuditHandler implements ActionHandler<ReviseInfo, AuditResult> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyRiskAuditHandler.class);

    @Override
    public AuditResult process(ActionChain chain, ProcessContext<ReviseInfo> context) {

        LoggerUtil.info(logger, "NotifyRiskAuditHandler begin to execute, context", context);

        ReviseInfo parameter = param(context);

        LoggerUtil.info(logger, "2nd 通知风控审核");

        LoggerUtil.info(logger, "NotifyRiskAuditHandler parameter is ", parameter);

        Integer handlerParam1 = results(context, UpdateRegisterStatusHandler.class);

        LoggerUtil.info(logger, "handlerParam1 from UpdateRegisterStatusHandler.class is ",
            handlerParam1);

        long operatorId = 666888;
        String name = "info";
        String remark = "audit approve.";

        AuditResult result = new AuditResult(operatorId, name, remark);

        return result;
    }

}