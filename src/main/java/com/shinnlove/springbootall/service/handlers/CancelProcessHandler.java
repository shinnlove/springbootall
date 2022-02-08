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

/**
 * @author Tony Zhao
 * @version $Id: CancelProcessHandler.java, v 0.1 2022-02-08 7:08 PM Tony Zhao Exp $$
 */
@Service
public class CancelProcessHandler implements ActionHandler<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(CancelProcessHandler.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String process(ActionChain chain, ProcessContext<String> context) {
        return null;
    }

}