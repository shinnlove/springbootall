/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.core.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shinnlove.springbootall.dao.ProcessTemplateMetadataDao;
import com.shinnlove.springbootall.dao.model.ProcessTemplateMetadataPo;
import com.shinnlove.springbootall.dao.model.ProcessTemplateMetadataPoExample;
import com.shinnlove.springbootall.process.core.ProcessTemplateMetadataCoreService;
import com.shinnlove.springbootall.process.model.template.ProcessTemplate;
import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.exception.SystemException;

/**
 * Process template metadata service implementation.
 *
 * @author Tony Zhao
 * @version $Id: ProcessTemplateMetadataCoreServiceImpl.java, v 0.1 2021-07-06 6:22 PM Tony Zhao Exp $$
 */
@Service
public class ProcessTemplateMetadataCoreServiceImpl implements ProcessTemplateMetadataCoreService {

    @Autowired
    private ProcessTemplateMetadataDao processTemplateMetadataDao;

    @Override
    public ProcessTemplate getTemplateMetadata(int templateId) {

        ProcessTemplateMetadataPoExample example = new ProcessTemplateMetadataPoExample();
        ProcessTemplateMetadataPoExample.Criteria criteria = example.or();
        criteria.andTemplateIdEqualTo(templateId);

        List<ProcessTemplateMetadataPo> templateMetadataPos = processTemplateMetadataDao
            .selectByExample(example);
        if (CollectionUtils.isEmpty(templateMetadataPos)) {
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, "根据template_id没有查询到模板");
        }

        ProcessTemplateMetadataPo metadataPo = templateMetadataPos.get(0);
        ProcessTemplate template = new ProcessTemplate();
        BeanUtils.copyProperties(metadataPo, template);

        return template;
    }

}