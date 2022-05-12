/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

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
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseItem;
import com.shinnlove.springbootall.service.models.ReviseUpdateOrder;

/**
 * 改价成功修改订单金额。
 * 
 * @author Tony Zhao
 * @version $Id: ReviseCompleteModifyPriceHandler.java, v 0.1 2022-03-09 9:22 PM Tony Zhao Exp $$
 */
@Service
public class ReviseCompleteModifyPriceHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        ReviseAttitude attitude = param(context);
        int orderId = attitude.getOrderId();

        List<ReviseItem> reviseItems = results(context, QueryReviseItemHandler.class);

        ReviseUpdateOrder reviseUpdateOrder = new ReviseUpdateOrder();

        for (ReviseItem item : reviseItems) {

            int type = item.getItemType();

            if (ReviseItemType.ORDER_PRICE.getCode() == type) {
                reviseUpdateOrder.setPrice(item.getAfterValue());
            } else if (ReviseItemType.SERVICE_FEE.getCode() == type) {
                reviseUpdateOrder.setServiceFee(item.getAfterValue());
            } else if (ReviseItemType.ORDER_EXPENSE.getCode() == type) {
                reviseUpdateOrder.setActualCostPrice(item.getAfterValue());
            } else if (ReviseItemType.UPPER_PROFIT.getCode() == type) {
                reviseUpdateOrder.setActualUpperPrice(item.getAfterValue());
            } else if (ReviseItemType.SERVICE_PROVIDER_INCOME.getCode() == type) {
                reviseUpdateOrder.setServiceProviderIncome(item.getAfterValue());
            }

        }

        // 更新价格
        return 1;
    }

}