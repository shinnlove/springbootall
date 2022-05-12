/**
 * bilibili.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers.pipeline;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.models.PickupMessage;

/**
 * 稿件视频上线处理。
 * 
 * @author Tony Zhao
 * @version $Id: DraftLaunchVideoHandler.java, v 0.1 2022-05-05 9:27 PM Tony Zhao Exp $$
 */
@Service
public class DraftLaunchVideoHandler implements ActionHandler<PickupMessage, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DraftLaunchVideoHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "DraftLaunchVideoHandler handler executed");

        List<Integer> orderIds = results(context, DraftOrderExistHandler.class);
        PickupMessage pickupMessage = param(context);
        long objId = pickupMessage.getObjId();

        return 1;
    }

}