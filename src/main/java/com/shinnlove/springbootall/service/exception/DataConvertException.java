/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.exception;

import com.bilibili.universal.util.code.SystemResultCode;
import com.bilibili.universal.util.exception.SystemException;

/**
 * Data convert system exception especially for NPE happened in model convert.
 *
 * @author Tony Zhao
 * @version $Id: DataConvertException.java, v 0.1 2021-08-23 3:07 PM Tony Zhao Exp $$
 */
public class DataConvertException extends SystemException {

    public DataConvertException(SystemResultCode resultCode, Throwable cause, String message) {
        super(resultCode, cause, message);
    }

    public DataConvertException(SystemResultCode resultCode, SystemException cause) {
        super(resultCode, cause);
    }

    public DataConvertException(String message, Throwable throwable) {
        super(message, throwable);
    }

}