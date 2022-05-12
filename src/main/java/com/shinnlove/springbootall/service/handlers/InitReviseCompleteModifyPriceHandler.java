/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.enums.ReviseItemType;
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;
import com.shinnlove.springbootall.service.models.ReviseUpdateOrder;
import com.shinnlove.springbootall.service.models.SubmitReviseItem;

/**
 * 初始化即生效改价，更新订单上价格。
 *
 * @author Tony Zhao
 * @version $Id: InitReviseCompleteModifyPriceHandler.java, v 0.1 2022-03-18 11:14 AM Tony Zhao Exp $$
 */
@Service
public class InitReviseCompleteModifyPriceHandler implements
                                                  ActionHandler<ReviseEntireInfo, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseEntireInfo entireInfo = param(context);
        int orderId = entireInfo.getOrderId();
        List<SubmitReviseItem> submitItems = entireInfo.getReviseItems();

        ReviseUpdateOrder reviseUpdateOrder = new ReviseUpdateOrder();

        for (SubmitReviseItem item : submitItems) {
            int type = item.getReviseItemType();
            BigDecimal newValue = item.getReviseValueAfter();

            if (ReviseItemType.ORDER_PRICE.getCode() == type) {
                reviseUpdateOrder.setPrice(newValue);
            } else if (ReviseItemType.SERVICE_FEE.getCode() == type) {
                reviseUpdateOrder.setServiceFee(newValue);
            } else if (ReviseItemType.ORDER_EXPENSE.getCode() == type) {
                reviseUpdateOrder.setActualCostPrice(newValue);
            } else if (ReviseItemType.UPPER_PROFIT.getCode() == type) {
                reviseUpdateOrder.setActualUpperPrice(newValue);
            } else if (ReviseItemType.SERVICE_PROVIDER_INCOME.getCode() == type) {
                reviseUpdateOrder.setServiceProviderIncome(newValue);
            }
        }

        return 1;
    }

}