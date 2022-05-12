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
 * 稿件审核驳回时更新补绑定理由。
 *
 * @author Tony Zhao
 * @version $Id: UpdateRebindRejectReasonHandler.java, v 0.1 2022-05-05 8:47 PM Tony Zhao Exp $$
 */
@Service
public class UpdateRebindRejectReasonHandler implements ActionHandler<PickupMessage, Integer> {

    private static final Logger logger = LoggerFactory
        .getLogger(UpdateRebindRejectReasonHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "UpdateRebindRejectReasonHandler handler executed");

        PickupMessage pickupMessage = param(context);
        long objId = pickupMessage.getObjId();
        // 这里的remark也是reject reason
        String remark = pickupMessage.getRemark();

        return 1;
    }

}