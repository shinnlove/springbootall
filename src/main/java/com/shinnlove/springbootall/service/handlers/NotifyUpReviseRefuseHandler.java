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
import com.shinnlove.springbootall.service.enums.ReviseStatus;
import com.shinnlove.springbootall.service.enums.ReviseType;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * 改价拒绝UP主侧消息通知。
 * 
 * @author Tony Zhao
 * @version $Id: NotifyUpReviseRefuseHandler.java, v 0.1 2022-01-25 3:42 PM Tony Zhao Exp $$
 */
@Service
public class NotifyUpReviseRefuseHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyUpReviseRefuseHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "NotifyUpReviseRefuseHandler handler executed");

        // 变迁的改价源状态
        int srcReviseStatus = context.getSourceStatus();
        ReviseRegister reviseRegister = results(context, QueryReviseInfoHandler.class);

        if (ReviseStatus.AD_CONFIRM.getCode() != srcReviseStatus
            || ReviseStatus.UPPER_CONFIRM.getCode() != srcReviseStatus) {
            // 自助改价 2 => 5 的时候发送给UP主，而后台改价 2 => 5 时 UP主还不可见 无感知
            // 无论是客户拒绝还是运营拒绝，UP主都要感知，所以不是自助改价不是客户确认都不要
            chain.continue0();
        }

        if (reviseRegister.getReviseType().equals(ReviseType.PLATFORM.getCode())
            && ReviseStatus.AD_CONFIRM.getCode() == srcReviseStatus) {
            // 后台改价ad拒绝UP主不需要知道
            chain.continue0();
        }

        // 消息体
        CmOrderPo orderPo = results(context, QueryOrderInfo2Handler.class);

        // 自助改价场景，客户拒绝了改价，改价状态变为“客户已拒绝”时，发送给个人UP主/MCN机构

        return 1;
    }

}