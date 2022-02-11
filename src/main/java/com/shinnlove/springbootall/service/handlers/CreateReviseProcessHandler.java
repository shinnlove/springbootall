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
 * Process 30004 service.
 * 
 * @author Tony Zhao
 * @version $Id: CreateReviseProcessHandler.java, v 0.1 2022-02-08 6:52 PM Tony Zhao Exp $$
 */
@Service
public class CreateReviseProcessHandler implements ActionHandler<ReviseInfo, String> {

    private static final Logger logger = LoggerFactory.getLogger(CreateReviseProcessHandler.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String process(ActionChain chain, ProcessContext<ReviseInfo> context) {

        LoggerUtil.info(logger, "NotifyAdvertiserConfirmHandler begin to execute, context",
            context);

        return null;
    }

}