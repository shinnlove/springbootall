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
import com.shinnlove.springbootall.service.models.CmOrderPo;
import com.shinnlove.springbootall.service.models.ReviseAttitude;
import com.shinnlove.springbootall.service.models.ReviseItem;

/**
 * Refresh union publish price if exists.
 *
 * @author Tony Zhao
 * @version $Id: RefreshExistingUnionPublishHandler.java, v 0.1 2022-03-17 10:01 PM Tony Zhao Exp $$
 */
@Service
public class RefreshExistingUnionPublishHandler implements ActionHandler<ReviseAttitude, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseAttitude> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        // 订单与改价项
        CmOrderPo orderPo = results(context, QueryOrderInfo2Handler.class);
        List<ReviseItem> items = results(context, QueryReviseItemHandler.class);

        // 改订单金额了吗
        boolean modifiedOrder = false;
        for (ReviseItem i : items) {
            if (i.getItemType().equals(ReviseItemType.ORDER_PRICE.getCode())) {
                modifiedOrder = true;
                break;
            }
        }

        // 更新过价格且关联联合投稿才更新联投价格

        return 0;
    }

}