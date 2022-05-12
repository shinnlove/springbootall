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
import com.bilibili.universal.process.model.context.DataContext;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;

/**
 * @author Tony Zhao
 * @version $Id: ReviseRegisterParentHandler.java, v 0.1 2022-03-06 12:35 PM Tony Zhao Exp $$
 */
@Service
public class ReviseRegisterParentHandler implements ActionHandler<ReviseEntireInfo, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ReviseRegisterParentHandler.class);

    @Override
    public Long process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // parameters need
        ReviseEntireInfo entireInfo = param(context);
        long refUniqueNo = context.getRefUniqueNo();

        int destination = context.getDestinationStatus();
        DataContext dataContext = context.getDataContext();
        int operatorType = dataContext.getOperatorType();
        long operatorId = dataContext.getOperatorId();
        String operator = dataContext.getOperator();
        String remark = dataContext.getRemark();

        CmOrderPo orderPo = results(context, QueryOrderInfoHandler.class);
        long orderSnapshotId = results(context, ReviseOrderSnapshotHandler.class);

        return 1L;
    }

}