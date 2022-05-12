/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.exception;

import com.bilibili.universal.process.ex.StatusBreakException;

/**
 * 忽略消息不再重试的错误。
 *
 * @author Tony Zhao
 * @version $Id: MessageIgnoreException.java, v 0.1 2022-04-27 3:45 PM Tony Zhao Exp $$
 */
public class MessageIgnoreException extends StatusBreakException {

    public MessageIgnoreException(String message) {
        super(message);
    }

}