/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.common.AssertUtil;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.enums.ReviseItemType;
import com.shinnlove.springbootall.service.handlers.pipeline.DeleteFinalDraftHandler;
import com.shinnlove.springbootall.service.models.ReviseEntireInfo;
import com.shinnlove.springbootall.service.models.ReviseItem;
import com.shinnlove.springbootall.service.models.SubmitReviseItem;

/**
 * @author Tony Zhao
 * @version $Id: ReviseItemInitHandler.java, v 0.1 2022-03-06 12:37 PM Tony Zhao Exp $$
 */
@Service
public class ReviseItemInitHandler implements ActionHandler<ReviseEntireInfo, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<ReviseEntireInfo> context) {

        LoggerUtil.warn(logger, "AdAuthBindAvIdHandler handler executed");

        Map<Integer, ProcessContext> childrenContextMap = context.getChildrenContext();

        long parentRefUniqueNo = context.getRefUniqueNo();

        ReviseEntireInfo entireInfo = param(context);
        String remark = entireInfo.getRemark();
        List<SubmitReviseItem> submits = entireInfo.getReviseItems();
        int count = 0;

        for (SubmitReviseItem s : submits) {
            ReviseItem item = new ReviseItem();

            int reviseType = s.getReviseItemType();
            ReviseItemType itemType = ReviseItemType.getByCode(reviseType);

            int tplId = itemType.getTemplateId();
            ProcessContext child = childrenContextMap.get(tplId);

            item.setReviseNo(child.getRefUniqueNo());
            item.setItemType(reviseType);
            item.setBeforeValue(s.getReviseValueBefore());
            item.setAfterValue(s.getReviseValueAfter());
            item.setReviseParentNo(parentRefUniqueNo);
            item.setRemark(remark);

            long result = 1L;
            if (result > 0) {
                count++;
            }
        }

        AssertUtil.isTrue(count == submits.size());

        return 1;
    }

}