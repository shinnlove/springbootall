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
 * 取消订单补绑定。
 * 这是稿件补绑定监听稿件原生事件的逻辑、这里删稿的时候也要处理下稿件重审的事情。
 *
 * @author Tony Zhao
 * @version $Id: CancelOrderRebindHandler.java, v 0.1 2022-05-05 9:13 PM Tony Zhao Exp $$
 */
@Service
public class CancelOrderRebindHandler implements ActionHandler<PickupMessage, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(CancelOrderRebindHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "CancelOrderRebindHandler handler executed");

        PickupMessage pickupMessage = param(context);

        return 1;
    }

}