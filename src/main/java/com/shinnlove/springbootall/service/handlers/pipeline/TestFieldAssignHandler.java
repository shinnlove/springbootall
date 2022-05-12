/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.annotation.ValueField;
import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;

/**
 * 测试field assign正确性的handler.
 * 
 * @author Tony Zhao
 * @version $Id: TestFieldAssignHandler.java, v 0.1 2022-05-10 3:48 PM Tony Zhao Exp $$
 */
@ValueField("objId")
@Service
public class TestFieldAssignHandler implements ActionHandler<Long, Long> {

    private static final Logger logger = LoggerFactory.getLogger(TestFieldAssignHandler.class);

    @Override
    public Long process(ActionChain chain, ProcessContext<Long> context) {

        LoggerUtil.warn(logger, "TestFieldAssignHandler handler executed");

        Long objId = param(context);

        LoggerUtil.info(logger, "TestFieldAssignHandler correct get objId = ", objId);

        return objId;
    }

}