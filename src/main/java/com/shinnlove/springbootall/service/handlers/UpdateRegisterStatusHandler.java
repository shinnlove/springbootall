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
import com.shinnlove.springbootall.service.biz.model.ReviseInfo;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: UpdateRegisterStatusHandler.java, v 0.1 2022-01-25 3:35 PM Tony Zhao Exp $$
 */
@Service
public class UpdateRegisterStatusHandler implements ActionHandler<ReviseInfo, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateRegisterStatusHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseInfo> context) {

        LoggerUtil.info(logger, "UpdateRegisterStatusHandler begin to execute, context", context);

        LoggerUtil.info(logger, "1st execute NotifyRiskAuditHandler handler");

        ReviseInfo info = param(context);

        LoggerUtil.info(logger, "name=", info);

        return 10;
    }

}