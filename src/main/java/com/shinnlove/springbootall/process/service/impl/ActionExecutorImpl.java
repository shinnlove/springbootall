/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.chain.ActionChain;
import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler2nd;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.process.service.ActionExecutor;

/**
 * Action proceed implementation.
 *
 * @author Tony Zhao
 * @version $Id: ActionExecutorImpl.java, v 0.1 2021-07-06 5:36 PM Tony Zhao Exp $$
 */
//@Service
public class ActionExecutorImpl implements ActionExecutor {

    @Override
    public void proceed(ProcessContext context, List<ActionHandler2nd> handlers) {
        // no handlers no executions, exception will not execute
        if (CollectionUtils.isEmpty(handlers)) {
            return;
        }

        // use a new chain to process all handlers business logic recursively
        ActionChain ac = new ActionChain(handlers);
        ac.process(context);
    }

}