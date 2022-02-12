/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.biz.revise.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.model.context.ProcessContext;
import com.shinnlove.springbootall.process.no.SnowflakeIdWorker;
import com.shinnlove.springbootall.process.pipeline.PipelineService;
import com.shinnlove.springbootall.process.service.StatusMachine2ndService;
import com.shinnlove.springbootall.service.biz.model.ApproveInfo;
import com.shinnlove.springbootall.service.biz.model.ComplexInfo;
import com.shinnlove.springbootall.service.biz.model.ReviseInfo;
import com.shinnlove.springbootall.service.biz.revise.RevisePriceService;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: RevisePriceServiceImpl.java, v 0.1 2022-02-10 2:48 PM Tony Zhao Exp $$
 */
@Service
public class RevisePriceServiceImpl implements RevisePriceService {

    /** logger */
    private static final Logger     logger = LoggerFactory.getLogger(RevisePriceServiceImpl.class);

    /** snowflake id generator */
    @Autowired
    private SnowflakeIdWorker       snowflakeIdWorker;

    /** status machine service */
    @Autowired
    private StatusMachine2ndService statusMachine2ndService;

    /** pipeline service. */
    @Autowired
    private PipelineService         pipelineService;

    @Override
    public long submitRevise(int itemType, BigDecimal before, BigDecimal after, String operator) {
        ReviseInfo reviseInfo = new ReviseInfo(itemType, before, after, 123456, "Tony",
            "create order revise");
        ApproveInfo approveInfo = new ApproveInfo(1, 123456, operator, "This is remark.");
        ComplexInfo complexInfo = new ComplexInfo(reviseInfo, approveInfo);

        long uniqueBizNo = snowflakeIdWorker.nextId();
        DataContext<ComplexInfo> dataContext = new DataContext<>(complexInfo);

        long processNo = statusMachine2ndService.initProcess(itemType, uniqueBizNo, dataContext);

        LoggerUtil.info(logger, "Process initialized, processNo=", processNo);

        return processNo;
    }

    @Override
    public long auditRevise(int actionId, long refUniqueNo, int approve, String operator) {
        ApproveInfo info = new ApproveInfo(approve, 123456, operator, "This is remark.");
        DataContext<ApproveInfo> dataContext = new DataContext<>(info);

        ProcessContext context = statusMachine2ndService.proceedProcess(actionId, refUniqueNo,
            dataContext);

        LoggerUtil.info(logger, "Process proceeded, context=", context);

        return 1L;
    }

    @Override
    public long pipelineAudit(int actionId) {
        Object result = pipelineService.doPipeline(actionId);
        return Long.parseLong(String.valueOf(result));
    }

}