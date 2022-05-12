/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.exception;

import com.bilibili.universal.util.code.SystemResultCode;
import com.bilibili.universal.util.exception.SystemException;

/**
 * 消息执行业务出错。
 *
 * @author Tony Zhao
 * @version $Id: MessageExecuteException.java, v 0.1 2022-04-27 3:48 PM Tony Zhao Exp $$
 */
public class MessageExecuteException extends SystemException {

    /**
     * Constructor with result code, cause and message.
     *
     * @param resultCode
     * @param cause
     * @param message
     */
    public MessageExecuteException(SystemResultCode resultCode, Throwable cause, String message) {
        super(resultCode, cause, message);
    }

}