/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseItem;

/**
 * 查询改价项处理器。
 * 
 * @author Tony Zhao
 * @version $Id: QueryReviseItemHandler.java, v 0.1 2022-03-10 11:48 AM Tony Zhao Exp $$
 */
@Service
public class QueryReviseItemHandler implements ActionHandler<ReviseAttitude, List<ReviseItem>> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public List<ReviseItem> process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseAttitude attitude = param(context);

        long refUniqueNo = attitude.getRefUniqueNo();

        return new ArrayList<>();
    }

}