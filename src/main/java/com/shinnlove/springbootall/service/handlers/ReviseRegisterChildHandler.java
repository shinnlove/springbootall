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
 * @version $Id: ReviseRegisterChildHandler.java, v 0.1 2022-03-06 2:09 PM Tony Zhao Exp $$
 */
@Service
public class ReviseRegisterChildHandler implements ActionHandler<ReviseEntireInfo, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ReviseRegisterChildHandler.class);

    @Override
    public Long process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "ReviseRegisterChildHandler handler executed");

        // parameters need
        ReviseEntireInfo entireInfo = param(context);
        long refUniqueNo = context.getRefUniqueNo();
        ProcessContext parentContext = context.getParentContext();
        long parentRefUniqueNo = parentContext.getRefUniqueNo();

        int destination = context.getDestinationStatus();
        DataContext dataContext = context.getDataContext();
        int operatorType = dataContext.getOperatorType();
        long operatorId = dataContext.getOperatorId();
        String operator = dataContext.getOperator();
        String remark = dataContext.getRemark();

        long orderSnapshotId = -1;
        CmOrderPo orderPo = results(context, QueryOrderInfoHandler.class);

        // 注册revise register
        return 1L;
    }

}