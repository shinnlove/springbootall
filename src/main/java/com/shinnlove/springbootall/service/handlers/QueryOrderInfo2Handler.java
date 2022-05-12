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
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;

/**
 * 通过改价处理对象查询订单信息。
 * 
 * TODO: optimize this, use order id instead different domain model.
 * 
 * @author Tony Zhao
 * @version $Id: QueryOrderInfo2Handler.java, v 0.1 2022-03-10 11:45 AM Tony Zhao Exp $$
 */
@Service
public class QueryOrderInfo2Handler implements ActionHandler<ReviseAttitude, CmOrderPo> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public CmOrderPo process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseAttitude attitude = param(context);

        return new CmOrderPo();
    }

}