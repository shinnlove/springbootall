/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.revise.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.model.batch.BatchInitParam;
import com.bilibili.universal.process.model.batch.BatchInitResult;
import com.bilibili.universal.process.model.batch.InitParam;
import com.bilibili.universal.process.model.context.DataContext;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.process.no.SnowflakeIdWorker;
import com.bilibili.universal.process.service.StatusMachine2ndService;
import com.shinnlove.springbootall.process.enums.ActionType;
import com.shinnlove.springbootall.process.enums.TemplateType;
import com.shinnlove.springbootall.service.biz.model.ApproveInfo;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;
import com.shinnlove.springbootall.service.revise.model.UpperReviseInfo;
import com.shinnlove.springbootall.service.revise.service.ReviseService;
import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: ReviseServiceImpl.java, v 0.1 2022-02-24 5:54 PM Tony Zhao Exp $$
 */
@Service
public class ReviseServiceImpl implements ReviseService {

    /** logger */
    private static final Logger     logger = LoggerFactory.getLogger(ReviseServiceImpl.class);

    /** snowflake id generator */
    @Autowired
    private SnowflakeIdWorker       snowflakeIdWorker;

    /** status machine service */
    @Autowired
    private StatusMachine2ndService statusMachine2ndService;

    @Override
    public long submitRevise(UpperReviseInfo upperReviseInfo) {

        BigDecimal upperDiscountRate = new BigDecimal(0.8);
        BigDecimal initServiceFeeRate = new BigDecimal(0.07);

        BigDecimal upperProfitBefore = upperReviseInfo.getUpperProfitBefore();
        BigDecimal upperProfitAfter = upperReviseInfo.getUpperProfitAfter();
        String reason = upperReviseInfo.getReason();

        BigDecimal orderPriceBefore = upperProfitBefore.divide(upperDiscountRate, 2,
            RoundingMode.HALF_UP);
        BigDecimal orderPriceAfter = upperProfitAfter.divide(upperDiscountRate, 2,
            RoundingMode.HALF_UP);

        BigDecimal serviceFeeBefore = orderPriceBefore.multiply(initServiceFeeRate);
        BigDecimal serviceFeeAfter = orderPriceAfter.multiply(initServiceFeeRate);

        BigDecimal orderExpenseBefore = upperProfitBefore;
        BigDecimal orderExpenseAfter = upperProfitAfter;

        ReviseEntireInfo reviseInfo = new ReviseEntireInfo();
        //        reviseInfo.setUpperProfitBefore(upperProfitBefore);
        //        reviseInfo.setUpperProfitAfter(upperProfitAfter);
        //
        //        reviseInfo.setOrderPriceBefore(orderPriceBefore);
        //        reviseInfo.setOrderPriceAfter(orderPriceAfter);
        //
        //        reviseInfo.setServiceFeeBefore(serviceFeeBefore);
        //        reviseInfo.setServiceFeeAfter(serviceFeeAfter);
        //
        //        reviseInfo.setOrderExpenseBefore(orderExpenseBefore);
        //        reviseInfo.setOrderExpenseAfter(orderExpenseAfter);

        DataContext<ReviseEntireInfo> dataContext = new DataContext(reviseInfo);

        int profitType = TemplateType.UPPER_PROFIT.getTemplateId();
        long profitNo = getUniqueNo();

        int orderType = TemplateType.ORDER_PRICE.getTemplateId();
        long orderNo = getUniqueNo();

        int feeType = TemplateType.SERVICE_FEE.getTemplateId();
        long feeNo = getUniqueNo();

        int expenseType = TemplateType.ORDER_EXPENSE.getTemplateId();
        long expenseNo = getUniqueNo();

        InitParam profitParam = new InitParam(profitType, profitNo, dataContext);
        InitParam orderParam = new InitParam(orderType, orderNo, dataContext);
        InitParam feeParam = new InitParam(feeType, feeNo, dataContext);
        InitParam expenseParam = new InitParam(expenseType, expenseNo, dataContext);

        List<InitParam> params = new ArrayList<>();
        params.add(profitParam);
        params.add(orderParam);
        params.add(feeParam);
        params.add(expenseParam);

        BatchInitParam batchInitParam = new BatchInitParam(params);

        BatchInitResult initResult = statusMachine2ndService.batchInitProcess(batchInitParam);

        return initResult.getParentRefUniqueNo();
    }

    @Override
    public long auditSuccess(long parentRefUniqueNo, int approve, String operator, String remark) {
        ApproveInfo info = new ApproveInfo(approve, 123456, operator, "This is remark.");
        DataContext<ApproveInfo> dataContext = new DataContext<>(info);

        int actionId = ActionType.AUDIT_ACCEPT_2_AD.getActionId();

        ProcessContext context = statusMachine2ndService.proceedProcess(actionId, parentRefUniqueNo,
            dataContext);

        LoggerUtil.info(logger, "Process proceeded, context=", context);

        return 1L;
    }

    private long getUniqueNo() {
        return snowflakeIdWorker.nextId();
    }

}