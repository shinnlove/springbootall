/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.pipeline.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.process.handler.interfaces.ActionHandler;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.process.pipeline.PipelineService;
import com.shinnlove.springbootall.process.service.ProcessMetadataService;
import com.shinnlove.springbootall.service.biz.model.ApproveInfo;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: PipelineServiceImpl.java, v 0.1 2021-12-28 11:38 PM Tony Zhao Exp $$
 */
@Service
public class PipelineServiceImpl implements PipelineService {

    private static final Logger    logger = LoggerFactory.getLogger(PipelineServiceImpl.class);

    /** pipeline executor. */
    @Autowired
    @Qualifier("processPool")
    private ExecutorService        executor;

    /** process template and status metadata autowired service */
    @Autowired
    private ProcessMetadataService processMetadataService;

    @Override
    public Object doPipeline(int actionId) {
        // prepare data context
        ApproveInfo approveInfo = new ApproveInfo(1, 123456, "tony", "This is remark.");
        DataContext<ApproveInfo> dataContext = new DataContext<>(approveInfo);
        ProcessContext<ApproveInfo> context = new ProcessContext<>(dataContext);

        // prepare handlers
        List<ActionHandler> syncHandlers = processMetadataService.getExecutions(actionId, true);

        return execute(context, syncHandlers);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object execute(final ProcessContext context,
                           final List<ActionHandler> handlers) throws SystemException {

        CompletableFuture<Object> f = CompletableFuture.completedFuture(null);
        if (CollectionUtils.isEmpty(handlers)) {
            return doExecute(f);
        }

        int cursor = 0;
        while (cursor < handlers.size()) {
            final int i = cursor++;
            final ActionHandler handler = handlers.get(i);
            f = f.thenCompose(previous -> CompletableFuture.supplyAsync(() -> {
                if (i > 0) {
                    handler.cache(handlers, i - 1, previous, context);
                }
                return handler.pipeline(context);
            }, executor));
        }

        return doExecute(f);
    }

    @SuppressWarnings("rawtypes")
    public Object doExecute(CompletableFuture future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            LoggerUtil.error(logger, e, e);
        } catch (Exception e) {
            LoggerUtil.error(logger, e, e);
        }
        return null;
    }

}