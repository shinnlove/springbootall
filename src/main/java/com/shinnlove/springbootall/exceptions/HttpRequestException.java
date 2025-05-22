/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.exceptions;

import lombok.Data;

/**
 * Http请求错误。
 *
 * @author Tony Zhao
 * @version $Id: HttpRequestException.java, v 0.1 2024-10-15 17:17 Tony Zhao Exp $$
 */
@Data
public class HttpRequestException extends RuntimeException {

    private BusinessCode businessCode;

    public HttpRequestException(BusinessCode businessCode) {
        super(businessCode.getMessage());
        this.businessCode = businessCode;
    }

    public HttpRequestException(BusinessCode businessCode, Throwable t) {
        super(businessCode.getMessage() + t.getMessage(), t);
        this.businessCode = businessCode;
    }

    public HttpRequestException(BusinessCode businessCode, String cause) {
        super(businessCode.getMessage() + cause);
        this.businessCode = businessCode;
    }

}