/**
 * bilibili.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.models.ArchiveBindMessageInfo;
import com.shinnlove.springbootall.service.models.ArchiveInfo;
import com.shinnlove.springbootall.service.models.PickupMessage;

/**
 * 查询稿件处理。
 * 
 * @author Tony Zhao
 * @version $Id: QueryUploadArchiveHandler.java, v 0.1 2022-04-29 2:31 PM Tony Zhao Exp $$
 */
@Service
public class QueryUploadArchiveHandler implements ActionHandler<PickupMessage, ArchiveInfo> {

    private static final Logger logger = LoggerFactory.getLogger(QueryUploadArchiveHandler.class);

    @Override
    public ArchiveInfo process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "QueryUploadArchiveHandler handler executed");

        ArchiveBindMessageInfo messageInfo = results(context, ParseDraftUploadHandler.class);

        // 查询稿件中心的标题
        ArchiveInfo archiveInfo = new ArchiveInfo();

        return archiveInfo;
    }

}