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
 * @version $Id: NotifyAdvertiserHandler.java, v 0.1 2022-01-25 3:39 PM Tony Zhao Exp $$
 */
@Service
public class NotifyAdvertiserHandler implements ActionHandler<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyAdvertiserHandler.class);

    @Override
    public String process(ActionChain chain, ProcessContext<String> context) {

        LoggerUtil.info(logger, "NotifyAdvertiserHandler begin to execute, context", context);

        return null;
    }

}