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
import com.shinnlove.springbootall.service.enums.RevisePolicyAuditStatus;
import com.shinnlove.springbootall.service.enums.ReviseStatus;
import com.shinnlove.springbootall.service.models.ReviseAttitude;

/**
 * 更新改价记录的审核状态。
 * 
 * @author Tony Zhao
 * @version $Id: UpdateReviseAuditStatusHandler.java, v 0.1 2022-03-10 4:00 PM Tony Zhao Exp $$
 */
@Service
public class UpdateReviseAuditStatusHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory
        .getLogger(UpdateReviseAuditStatusHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // 不是审核状态点关心的状态直接过
        int source = context.getSourceStatus();
        if (ReviseStatus.AUDIT.getCode() != source) {
            chain.continue0();
        }

        ReviseAttitude attitude = param(context);
        long refUniqueNo = attitude.getRefUniqueNo();

        // 有审核记录才更新审核表

        String operator = attitude.getOperator();

        // 更新审核表状态，只要不是拒绝，都算是审核通过
        int destination = context.getDestinationStatus();
        int auditStatus = RevisePolicyAuditStatus.APPROVE.getCode();
        if (ReviseStatus.REJECTED.getCode() == destination) {
            auditStatus = RevisePolicyAuditStatus.REFUSE.getCode();
        } else if (ReviseStatus.CANCELED.getCode() == destination) {
            auditStatus = RevisePolicyAuditStatus.CANCEL.getCode();
        }

        return 1;
    }

}