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
 * 订单发生变更通知Crm来更新。
 * 
 * @author Tony Zhao
 * @version $Id: NotifyCrmSyncHandler.java, v 0.1 2022-02-10 4:33 PM Tony Zhao Exp $$
 */
@Service
public class NotifyCrmSyncHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        int updateResult = results(context, ReviseCompleteModifyPriceHandler.class);
        if (updateResult < 0) {
            // 订单没有更新成功不往下发Crm同步消息
            chain.continue0();
        }

        ReviseAttitude attitude = param(context);
        CmOrderPo orderPo = results(context, QueryOrderInfo2Handler.class);

        // 发消息让Crm来同步订单更新        

        return 1;
    }

}