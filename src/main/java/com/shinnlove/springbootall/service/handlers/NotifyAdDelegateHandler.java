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
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;

/**
 * 运营代接单端同意通知接单端角色。
 * 
 * @author Tony Zhao
 * @version $Id: NotifyAdDelegateHandler.java, v 0.1 2022-01-25 3:39 PM Tony Zhao Exp $$
 */
@Service
public class NotifyAdDelegateHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseAttitude attitude = param(context);
        // 变迁的改价源状态
        int srcReviseStatus = context.getSourceStatus();

        if (!PickupOperatorType.OPERATING_PERSONNEL.getCode().equals(attitude.getOperatorType())
            || ReviseStatus.AD_CONFIRM.getCode() != srcReviseStatus) {
            // 非运营代操作、非客户确认源状态不发SMS
            return 0;
        }

        CmOrderPo orderPo = results(context, QueryOrderInfo2Handler.class);
        int att = attitude.getAttitude();

        return 1;
    }

}