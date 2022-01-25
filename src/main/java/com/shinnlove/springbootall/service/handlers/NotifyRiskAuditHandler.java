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
public class NotifyRiskAuditHandler implements ActionHandler<Void, Void> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyRiskAuditHandler.class);

    @Override
    public Void process(ActionChain chain, ProcessContext<Void> context) {

        LoggerUtil.info(logger, "NotifyRiskAuditHandler begin to execute, context", context);

        return null;
    }

}