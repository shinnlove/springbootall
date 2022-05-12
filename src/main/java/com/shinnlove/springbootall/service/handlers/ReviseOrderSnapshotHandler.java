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
 * @author Tony Zhao
 * @version $Id: ReviseOrderSnapshotHandler.java, v 0.1 2022-03-06 12:11 PM Tony Zhao Exp $$
 */
@Service
public class ReviseOrderSnapshotHandler implements ActionHandler<ReviseEntireInfo, Long> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Long process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // 取入参
        CmOrderPo orderPo = results(context, QueryOrderInfoHandler.class);

        // 订单快照保存，返回snapshot的id
        // fix order id

        return 1L;
    }

}