/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.process.chain.ActionChain;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.service.biz.model.ReviseInfo;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: NotifyCrmSyncHandler.java, v 0.1 2022-02-10 4:33 PM Tony Zhao Exp $$
 */
@Service
public class NotifyCrmSyncHandler implements ActionHandler<ReviseInfo, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(NotifyCrmSyncHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseInfo> context) {

        LoggerUtil.info(logger, "NotifyCrmSyncHandler begin to execute, context", context);

        ReviseInfo parameter = params(context);

        LoggerUtil.info(logger, "2nd 通知Crm来同步");

        LoggerUtil.info(logger, "NotifyCrmSyncHandler parameter is ", parameter);

        Integer handlerParam1 = result(context, UpdateRegisterStatusHandler.class);

        LoggerUtil.info(logger, "handlerParam1 from UpdateRegisterStatusHandler.class is ",
            handlerParam1);

        return 1;
    }

}