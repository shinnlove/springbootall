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
import com.shinnlove.springbootall.service.enums.PickupOperatorType;
import com.shinnlove.springbootall.service.enums.ReviseStatus;
import com.shinnlove.springbootall.service.enums.ReviseType;
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * 后台改价运营代替UP主接受改价后，发送通知给UP主。
 * 
 * @author Tony Zhao
 * @version $Id: NotifyUpDelegateHandler.java, v 0.1 2022-01-25 3:42 PM Tony Zhao Exp $$
 */
@Service
public class NotifyUpDelegateHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // 变迁的改价源状态
        int srcReviseStatus = context.getSourceStatus();

        ReviseAttitude attitude = param(context);
        int operatorType = attitude.getOperatorType();
        int att = attitude.getAttitude();
        ReviseRegister reviseRegister = results(context, QueryReviseInfoHandler.class);

        if (!PickupOperatorType.OPERATING_PERSONNEL.getCode().equals(operatorType)
            || !reviseRegister.getReviseType().equals(ReviseType.PLATFORM.getCode())
            || ReviseStatus.UPPER_CONFIRM.getCode() != srcReviseStatus) {
            // 非UP主确认节点、非运营代操作直接返回、且自助改价也不会有后台代操作
            return 0;
        }

        // 代操作消息类型

        CmOrderPo orderPo = results(context, QueryOrderInfo2Handler.class);

        //运营代操作确认下游价格，改价状态变为“UP主已接受”时,发送给个人UP主/MCN机构

        return 1;
    }

}