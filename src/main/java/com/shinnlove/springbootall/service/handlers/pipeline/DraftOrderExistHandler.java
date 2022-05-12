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
import com.google.common.collect.ImmutableList;
import com.shinnlove.springbootall.service.models.PickupMessage;

/**
 * 判断稿件对应订单是否存在。
 *
 * @author Tony Zhao
 * @version $Id: DraftOrderExistHandler.java, v 0.1 2022-05-05 8:28 PM Tony Zhao Exp $$
 */
@Service
public class DraftOrderExistHandler implements ActionHandler<PickupMessage, List<Integer>> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public List<Integer> process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        PickupMessage pickupMessage = param(context);
        long objId = pickupMessage.getObjId();

        return ImmutableList.of(1);
    }

}