/**
 * bilibili.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers.pipeline;

import java.sql.Timestamp;

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
 * 存储终稿处理器。
 * 
 * @author Tony Zhao
 * @version $Id: StoreFinalDraftHandler.java, v 0.1 2022-04-29 2:32 PM Tony Zhao Exp $$
 */
@Service
public class StoreFinalDraftHandler implements ActionHandler<PickupMessage, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(StoreFinalDraftHandler.class);

    @Override
    public Integer process(ActionChain chain, ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "StoreFinalDraftHandler handler executed");

        ArchiveBindMessageInfo messageInfo = results(context, ParseDraftUploadHandler.class);
        ArchiveInfo archiveInfo = results(context, QueryUploadArchiveHandler.class);

        Timestamp mtime = null;
        try {
            // 取上传时间
            mtime = Timestamp.valueOf(messageInfo.getMtime());
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "getTimeFromString error str ", messageInfo.getMtime());
        }

        return 1;
    }

}