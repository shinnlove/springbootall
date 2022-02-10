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
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: NotifyRiskAuditHandler.java, v 0.1 2022-01-25 3:36 PM Tony Zhao Exp $$
 */
@Service
public class NotifyRiskAuditHandler implements ActionHandler<String, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyRiskAuditHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<String> context) {

        LoggerUtil.info(logger, "NotifyRiskAuditHandler begin to execute, context", context);

        String parameter = params(context);

        LoggerUtil.info(logger, "2nd execute NotifyRiskAuditHandler handler");

        LoggerUtil.info(logger, "parameter is ", parameter);

        Integer handlerParam1 = result(context, CreateReviseProcessHandler.class);

        LoggerUtil.info(logger, "handlerParam1 is ", handlerParam1);

        return 1;
    }

}