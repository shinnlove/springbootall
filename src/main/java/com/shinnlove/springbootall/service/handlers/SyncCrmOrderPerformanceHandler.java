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
 * @version $Id: SyncCrmOrderPerformanceHandler.java, v 0.1 2022-01-25 3:37 PM Tony Zhao Exp $$
 */
@Service
public class SyncCrmOrderPerformanceHandler implements ActionHandler<Void, Void> {

    private static final Logger logger = LoggerFactory
        .getLogger(SyncCrmOrderPerformanceHandler.class);

    @Override
    public Void process(ActionChain chain, ProcessContext<Void> context) {

        LoggerUtil.info(logger, "SyncCrmOrderPerformanceHandler begin to execute, context",
            context);

        return null;
    }

}