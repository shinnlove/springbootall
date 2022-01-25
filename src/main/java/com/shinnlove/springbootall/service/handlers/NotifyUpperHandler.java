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
 * @version $Id: NotifyUpperHandler.java, v 0.1 2022-01-25 3:42 PM Tony Zhao Exp $$
 */
@Service
public class NotifyUpperHandler implements ActionHandler<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyUpperHandler.class);

    @Override
    public String process(ActionChain chain, ProcessContext<String> context) {

        LoggerUtil.info(logger, "NotifyUpperHandler begin to execute, context", context);

        return null;
    }

}