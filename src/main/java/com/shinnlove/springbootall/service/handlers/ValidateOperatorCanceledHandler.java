/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import com.shinnlove.springbootall.process.chain.ActionChain;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.service.enums.UpperMissionStatus;
import com.shinnlove.springbootall.service.models.UpperMissionBC;
import com.shinnlove.springbootall.util.log.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Tony Zhao
 * @version $Id: ValidateOperatorCanceledHandler.java, v 0.1 2022-01-11 5:35 PM Tony Zhao Exp $$
 */
@Service
public class ValidateOperatorCanceledHandler implements ActionHandler<UpperMissionBC, Void> {

    private static final Logger logger = LoggerFactory
            .getLogger(ValidateOperatorCanceledHandler.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Void process(ActionChain chain, ProcessContext context) {
        LoggerUtil.info(logger, "ValidateOperatorCanceledHandler begin to execute, context",
                context);

        UpperMissionBC upperMissionBC = params(context);

        if (UpperMissionStatus.CANCELED.getCode().equals(upperMissionBC.getJoinStatus())) {
            chain.break0();
        }

        return null;
    }

}
