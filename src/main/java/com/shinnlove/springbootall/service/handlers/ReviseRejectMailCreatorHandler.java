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
import com.shinnlove.springbootall.service.enums.ReviseStatus;
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseRegister;

/**
 * 改价拒绝邮件通知发起者。
 *
 * @author Tony Zhao
 * @version $Id: ReviseRejectMailCreatorHandler.java, v 0.1 2022-03-24 11:25 PM Tony Zhao Exp $$
 */
@Service
public class ReviseRejectMailCreatorHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory
        .getLogger(ReviseRejectMailCreatorHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        int destination = context.getDestinationStatus();
        if (!ReviseStatus.TERMINATED.contains(destination)) {
            chain.continue0();
        }

        long reviseNo = context.getRefUniqueNo();

        // 只有后台改价要通知

        ReviseRegister reviseRegister = results(context, QueryReviseInfoHandler.class);
        CmOrderPo orderPo = results(context, QueryOrderInfo2Handler.class);

        long orderNo = reviseRegister.getOrderNo();

        DataContext dataContext = context.getDataContext();
        String operator = dataContext.getOperator();
        String remark = dataContext.getRemark();

        // 发邮件

        return 1;
    }

}