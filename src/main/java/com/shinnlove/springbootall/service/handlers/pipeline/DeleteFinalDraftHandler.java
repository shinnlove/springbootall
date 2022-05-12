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
 * 删除终稿处理。
 *
 * @author Tony Zhao
 * @version $Id: DeleteFinalDraftHandler.java, v 0.1 2022-05-05 9:09 PM Tony Zhao Exp $$
 */
@Service
public class DeleteFinalDraftHandler implements ActionHandler<PickupMessage, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "DeleteFinalDraftHandler handler executed");

        List<Integer> orderIds = results(context, DraftOrderExistHandler.class);
        PickupMessage pickupMessage = param(context);
        // 这里的remark存的是aid
        String remark = pickupMessage.getRemark();

        return 1;
    }

}