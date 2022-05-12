/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.exception;

import com.bilibili.universal.util.code.SystemResultCode;
import com.bilibili.universal.util.exception.SystemException;

/**
 * An exception for soa service occurs errors.
 *
 * @author Tony Zhao
 * @version $Id: ServiceInvokeException.java, v 0.1 2021-12-02 4:28 PM Tony Zhao Exp $$
 */
public class ServiceInvokeException extends SystemException {

    /**
     * Constructor with result code, cause and message.
     *
     * @param resultCode
     * @param cause
     * @param message
     */
    public ServiceInvokeException(SystemResultCode resultCode, Throwable cause, String message) {
        super(resultCode, cause, message);
    }

}