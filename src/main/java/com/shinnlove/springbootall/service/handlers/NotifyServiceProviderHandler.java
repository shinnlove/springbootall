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
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: NotifyServiceProviderHandler.java, v 0.1 2022-01-25 3:42 PM Tony Zhao Exp $$
 */
@Service
public class NotifyServiceProviderHandler implements ActionHandler<String, String> {

    private static final Logger logger = LoggerFactory
        .getLogger(NotifyServiceProviderHandler.class);

    @Override
    public String process(ActionChain chain, ProcessContext<String> context) {

        LoggerUtil.info(logger, "NotifyServiceProviderHandler begin to execute, context", context);

        return null;
    }

}