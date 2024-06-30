/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.exceptions;

import lombok.Data;

/**
 * @author Tony Zhao
 * @version $Id: DBExecuteReturnException.java, v 0.1 2024-06-30 19:58 Tony Zhao Exp $$
 */
@Data
public class DBExecuteReturnException extends RuntimeException {

    private BusinessCode businessCode;

    public DBExecuteReturnException(BusinessCode businessCode) {
        super(businessCode.getMessage());
        this.businessCode = businessCode;
    }

    public DBExecuteReturnException(BusinessCode businessCode, Throwable t) {
        super(businessCode.getMessage() + t.getMessage(), t);
        this.businessCode = businessCode;
    }

}