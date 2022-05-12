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
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * 查询改价注册信息。
 * 
 * @author Tony Zhao
 * @version $Id: QueryReviseInfoHandler.java, v 0.1 2022-03-09 9:26 PM Tony Zhao Exp $$
 */
@Service
public class QueryReviseInfoHandler implements ActionHandler<ReviseAttitude, ReviseRegister> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public ReviseRegister process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseAttitude attitude = param(context);
        long refUniqueNo = attitude.getRefUniqueNo();

        return new ReviseRegister();
    }

}