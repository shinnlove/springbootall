/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.exceptions;

/**
 * @author Tony Zhao
 * @version $Id: ErrorCodeProvider.java, v 0.1 2024-06-30 19:56 Tony Zhao Exp $$
 */
public interface ErrorCodeProvider {

    int getCode();

    String getMessage();

}
