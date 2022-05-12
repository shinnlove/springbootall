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
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;

/**
 * 后台改价记录审核表。
 * 
 * @author Tony Zhao
 * @version $Id: ReviseAuditInitHandler.java, v 0.1 2022-03-06 12:16 PM Tony Zhao Exp $$
 */
@Service
public class ReviseAuditInitHandler implements ActionHandler<ReviseEntireInfo, Long> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Long process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseEntireInfo entireInfo = param(context);
        int orderId = entireInfo.getOrderId();
        long refUniqueNo = context.getRefUniqueNo();
        String remark = entireInfo.getRemark();
        String operator = entireInfo.getOperator();

        if (!entireInfo.isPlatform()) {
            // 自助改价不需要进审核表
            chain.continue0();
        }

        CmOrderPo orderPo = results(context, QueryOrderInfoHandler.class);
        long orderSnapshotId = results(context, ReviseOrderSnapshotHandler.class);
        boolean isManually = entireInfo.isNeedPolicyReview();

        return 1L;
    }
}
