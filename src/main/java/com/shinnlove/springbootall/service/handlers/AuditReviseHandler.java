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
import com.shinnlove.springbootall.service.biz.model.ApproveInfo;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: AuditReviseHandler.java, v 0.1 2022-02-11 4:14 PM Tony Zhao Exp $$
 */
@Service
public class AuditReviseHandler implements ActionHandler<ApproveInfo, String> {

    private static final Logger logger = LoggerFactory.getLogger(AuditReviseHandler.class);

    @Override
    public String process(ActionChain chain, ProcessContext<ApproveInfo> context) {

        LoggerUtil.info(logger, "AuditReviseHandler begin to execute, context", context);

        LoggerUtil.info(logger, "审核处理成功，同意并翻转状态.");

        return "Aha, this is audit approve attitude remark.";
    }

}