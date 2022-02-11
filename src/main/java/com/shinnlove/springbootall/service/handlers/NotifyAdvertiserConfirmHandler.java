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
 * @version $Id: NotifyAdvertiserConfirmHandler.java, v 0.1 2022-01-25 3:39 PM Tony Zhao Exp $$
 */
@Service
public class NotifyAdvertiserConfirmHandler implements ActionHandler<ReviseInfo, Integer> {

    private static final Logger logger = LoggerFactory
        .getLogger(NotifyAdvertiserConfirmHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseInfo> context) {

        LoggerUtil.info(logger, "NotifyAdvertiserConfirmHandler begin to execute, context",
            context);

        LoggerUtil.info(logger, "3rd通知广告主确认");

        AuditResult auditResult = result(context, NotifyRiskAuditHandler.class);

        LoggerUtil.info(logger, "auditResult=", auditResult);

        return 3000;
    }

}