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
import com.shinnlove.springbootall.service.biz.model.ComplexInfo;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: ParentRiskAuditHandler.java, v 0.1 2022-02-13 6:07 PM Tony Zhao Exp $$
 */
@Service
public class ParentRiskAuditHandler implements ActionHandler<ComplexInfo, String> {

    private static final Logger logger = LoggerFactory.getLogger(ParentRiskAuditHandler.class);

    @Override
    public String process(ActionChain chain, ProcessContext<ComplexInfo> context) {
        LoggerUtil.info(logger, "ParentRiskAuditHandler begin to execute, context", context);

        LoggerUtil.info(logger, "parent 2nd execute ParentRiskAuditHandler handler");

        ComplexInfo complexInfo = param(context);

        LoggerUtil.info(logger, "2nd parent complexInfo=", complexInfo);

        int previous = results(context, UpdateRegisterStatusHandler.class);

        LoggerUtil.info(logger, "2nd previous handler info=", previous);

        return "parent process completed";
    }
}