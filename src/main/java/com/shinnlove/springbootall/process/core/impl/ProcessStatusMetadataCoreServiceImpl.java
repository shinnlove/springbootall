/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.dao.ProcessStatusMetadataDao;
import com.shinnlove.springbootall.dao.model.ProcessStatusMetadataPo;
import com.shinnlove.springbootall.dao.model.ProcessStatusMetadataPoExample;
import com.shinnlove.springbootall.process.core.ProcessStatusMetadataCoreService;
import com.shinnlove.springbootall.process.model.status.ProcessStatus;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.common.CommonUtil;
import com.shinnlove.springbootall.util.exception.SystemException;

/**
 * @author Tony Zhao
 * @version $Id: ProcessStatusMetadataCoreServiceImpl.java, v 0.1 2021-07-06 6:13 PM Tony Zhao Exp $$
 */
//@Service
public class ProcessStatusMetadataCoreServiceImpl implements ProcessStatusMetadataCoreService {

    @Autowired
    private ProcessStatusMetadataDao processStatusMetadataDao;

    @Override
    public List<ProcessStatus> getProcessStatus(int templateId) {

        ProcessStatusMetadataPoExample example = new ProcessStatusMetadataPoExample();
        ProcessStatusMetadataPoExample.Criteria criteria = example.or();
        criteria.andTemplateIdEqualTo(templateId);

        List<ProcessStatusMetadataPo> statusMetadataPos = processStatusMetadataDao
            .selectByExample(example);
        if (CollectionUtils.isEmpty(statusMetadataPos)) {
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, "没有查询到对应模板id的status");
        }

        List<ProcessStatus> processStatusList = new ArrayList<>();
        for (ProcessStatusMetadataPo po : statusMetadataPos) {
            ProcessStatus processStatus = CommonUtil.copyObj(po, ProcessStatus.class);
            processStatusList.add(processStatus);
        }

        return processStatusList;
    }

}