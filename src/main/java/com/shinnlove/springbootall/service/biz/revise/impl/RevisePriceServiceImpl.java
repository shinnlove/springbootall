/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.biz.revise.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bilibili.universal.process.model.batch.BatchInitParam;
import com.bilibili.universal.process.model.batch.BatchInitResult;
import com.bilibili.universal.process.model.batch.InitParam;
import com.shinnlove.springbootall.process.enums.TemplateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.model.context.DataContext;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.process.no.SnowflakeIdWorker;
import com.bilibili.universal.process.pipeline.PipelineService;
import com.bilibili.universal.process.service.StatusMachine2ndService;
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
    public long submitMultipleRevise(BigDecimal before, BigDecimal after, String operator) {

        int id = TemplateType.ORDER_PRICE.getTemplateId();
        long refUniqueNo = snowflakeIdWorker.nextId();

        ReviseInfo reviseInfo = new ReviseInfo(id, before, after, 654321, "Tony",
            "create order revise");
        DataContext<ReviseInfo> cData = new DataContext<>(reviseInfo);

        ReviseInfo parentReviseInfo = new ReviseInfo(id, before, after, 876543, "Tony Father",
            "create parent order revise");
        DataContext<ReviseInfo> pData = new DataContext<>(parentReviseInfo);

        List<InitParam> ips = new ArrayList<>();
        InitParam ip = new InitParam(id, refUniqueNo, cData);
        ips.add(ip);

        BatchInitParam initParam = new BatchInitParam();
        initParam.setParams(ips);
        initParam.setParentDataContext(pData);

        BatchInitResult result = statusMachine2ndService.batchInitProcess(initParam);

        return result.getParentRefUniqueNo();
    }

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
    public Object pipelineAudit(int actionId) {
        // prepare data context
        DataContext<String> dataContext = new DataContext<>("This is a input parameter.");

        Object result = pipelineService.doPipeline(actionId, dataContext, context -> {
            System.out.println(context.getResultObject());
        });

        return result;
    }

}