/**
 * bilibili.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.models.PickupMessage;

/**
 * 稿件绑定关系的行为服务处理。
 * 
 * @author Tony Zhao
 * @version $Id: DraftBindingRecordHandler.java, v 0.1 2022-05-05 4:38 PM Tony Zhao Exp $$
 */
@Service
public class DraftBindingRecordHandler implements ActionHandler<PickupMessage, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        PickupMessage pickupMessage = param(context);

        return 1;
    }

}