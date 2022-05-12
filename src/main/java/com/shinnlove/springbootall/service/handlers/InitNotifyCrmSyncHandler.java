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
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;

/**
 * 初始化改价即改即生效通知Crm更新订单。
 * 
 * @author Tony Zhao
 * @version $Id: InitNotifyCrmSyncHandler.java, v 0.1 2022-03-18 11:17 AM Tony Zhao Exp $$
 */
@Service
public class InitNotifyCrmSyncHandler implements ActionHandler<ReviseEntireInfo, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(InitNotifyCrmSyncHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        int updateResult = results(context, InitReviseCompleteModifyPriceHandler.class);
        if (updateResult < 0) {
            // 订单没有更新成功不往下发Crm同步消息
            chain.continue0();
        }

        CmOrderPo orderPo = results(context, QueryOrderInfoHandler.class);

        return 1;
    }

}