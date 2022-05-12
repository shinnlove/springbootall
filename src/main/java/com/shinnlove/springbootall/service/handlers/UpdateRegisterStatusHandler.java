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
import com.shinnlove.springbootall.service.enums.ReviseSuccessEnum;
import com.shinnlove.springbootall.service.models.ReviseAttitude;

/**
 * 更新改价注册表的状态
 * 
 * @author Tony Zhao
 * @version $Id: UpdateRegisterStatusHandler.java, v 0.1 2022-03-10 3:00 PM Tony Zhao Exp $$
 */
@Service
public class UpdateRegisterStatusHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateRegisterStatusHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseAttitude attitude = param(context);
        long refUniqueNo = attitude.getRefUniqueNo();

        // 当前action要变迁的status
        int destination = context.getDestinationStatus();

        // 枚举映射...为了简化查询
        int flag = ReviseSuccessEnum.UNDERWAY.getCode();
        if (ReviseStatus.ACCEPTED.getCode() == destination) {
            flag = ReviseSuccessEnum.SUCCESS.getCode();
        } else if (ReviseStatus.REJECTED.getCode() == destination) {
            flag = ReviseSuccessEnum.FAIL.getCode();
        } else if (ReviseStatus.CANCELED.getCode() == destination) {
            flag = ReviseSuccessEnum.CANCEL.getCode();
        }

        // 更新两张注册表状态
        int sum = 0;

        // 父注册表状态

        // 子范状态需要先查询下是不是已经过了目标状态了，过了就不更新注册表..

        return sum;
    }

}