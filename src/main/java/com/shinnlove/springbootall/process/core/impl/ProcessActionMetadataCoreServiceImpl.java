/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.dao.ProcessActionMetadataDao;
import com.shinnlove.springbootall.dao.model.ProcessActionMetadataPo;
import com.shinnlove.springbootall.dao.model.ProcessActionMetadataPoExample;
import com.shinnlove.springbootall.process.core.ProcessActionMetadataCoreService;
import com.shinnlove.springbootall.process.model.action.ProcessAction;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.common.CommonUtil;
import com.shinnlove.springbootall.util.exception.SystemException;

/**
 * @author Tony Zhao
 * @version $Id: ProcessActionMetadataCoreServiceImpl.java, v 0.1 2021-07-26 10:34 PM Tony Zhao Exp $$
 */
@Service
public class ProcessActionMetadataCoreServiceImpl implements ProcessActionMetadataCoreService {

    @Autowired
    private ProcessActionMetadataDao processActionMetadataDao;

    @Override
    public List<ProcessAction> getProcessActions(int templateId) {

        ProcessActionMetadataPoExample example = new ProcessActionMetadataPoExample();
        ProcessActionMetadataPoExample.Criteria criteria = example.or();
        criteria.andTemplateIdEqualTo(templateId);

        List<ProcessActionMetadataPo> actionMetadataPos = processActionMetadataDao
            .selectByExample(example);
        if (CollectionUtils.isEmpty(actionMetadataPos)) {
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, "没有查询到对应模板id的actions");
        }

        List<ProcessAction> processActionList = new ArrayList<>();
        for (ProcessActionMetadataPo po : actionMetadataPos) {
            ProcessAction processAction = CommonUtil.copyObj(po, ProcessAction.class);
            processActionList.add(processAction);
        }

        return processActionList;
    }

}