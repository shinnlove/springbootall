/**
 * bilibili.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.handlers.pipeline;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.universal.util.log.LoggerUtil;
import com.shinnlove.springbootall.service.exception.MessageIgnoreException;
import com.shinnlove.springbootall.service.models.ArchiveBindMessageInfo;
import com.shinnlove.springbootall.service.models.PickupMessage;

/**
 * 解析上传稿件json消息体。
 * 
 * @author Tony Zhao
 * @version $Id: ParseDraftUploadHandler.java, v 0.1 2022-04-29 2:44 PM Tony Zhao Exp $$
 */
@Service
public class ParseDraftUploadHandler implements
                                     ActionHandler<PickupMessage, ArchiveBindMessageInfo> {

    private static final Logger logger = LoggerFactory.getLogger(ParseDraftUploadHandler.class);

    @Override
    public ArchiveBindMessageInfo process(ActionChain chain,
                                          ProcessContext<PickupMessage> context) {

        LoggerUtil.warn(logger, "ParseDraftUploadHandler handler executed");

        PickupMessage msg = param(context);
        // check ok: 这里直接使用domain model来序列化
        ArchiveBindMessageInfo messageInfo = JSON.parseObject(msg.getDomainModel(),
            ArchiveBindMessageInfo.class);

        if (Objects.isNull(messageInfo)) {
            throw new MessageIgnoreException("ignore this message");
        }

        return messageInfo;
    }

}