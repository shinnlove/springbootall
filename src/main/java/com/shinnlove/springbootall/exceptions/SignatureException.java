/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.exceptions;

import lombok.Data;

/**
 * @author Tony Zhao
 * @version $Id: SignatureException.java, v 0.1 2024-10-15 17:17 Tony Zhao Exp $$
 */
@Data
public class SignatureException extends RuntimeException {

    private BusinessCode businessCode;

    public SignatureException(BusinessCode businessCode) {
        super(businessCode.getMessage());
        this.businessCode = businessCode;
    }

    public SignatureException(BusinessCode businessCode, Throwable t) {
        super(businessCode.getMessage() + t.getMessage(), t);
        this.businessCode = businessCode;
    }

    public SignatureException(BusinessCode businessCode, String cause) {
        super(businessCode.getMessage() + cause);
        this.businessCode = businessCode;
    }

}