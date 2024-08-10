/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.exceptions;

import lombok.Data;

/**
 * @author Tony Zhao
 * @version $Id: DBAccessThrowException.java, v 0.1 2024-06-30 19:58 Tony Zhao Exp $$
 */
@Data
public class DBAccessThrowException extends RuntimeException {

    private BusinessCode businessCode;

    public DBAccessThrowException(BusinessCode businessCode) {
        super(businessCode.getMessage());
        this.businessCode = businessCode;
    }

    public DBAccessThrowException(BusinessCode businessCode, Throwable t) {
        super(businessCode.getMessage() + t.getMessage(), t);
        this.businessCode = businessCode;
    }

}