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
import com.bilibili.universal.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: AcceptProcessHandler.java, v 0.1 2022-02-08 7:03 PM Tony Zhao Exp $$
 */
@Service
public class AcceptProcessHandler implements ActionHandler<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(AcceptProcessHandler.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String process(ActionChain chain, ProcessContext<String> context) {

        LoggerUtil.warn(logger, "AcceptProcessHandler handler executed");

        return null;
    }

}