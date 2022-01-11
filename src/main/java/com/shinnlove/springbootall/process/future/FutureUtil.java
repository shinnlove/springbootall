/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.future;


import com.shinnlove.springbootall.util.code.SystemResultCode;
import com.shinnlove.springbootall.util.exception.SystemException;
import com.shinnlove.springbootall.util.log.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * @author Tony Zhao
 * @version $Id: FutureUtil.java, v 0.1 2021-08-04 6:20 PM Tony Zhao Exp $$
 */
public class FutureUtil {

    private final static Logger logger = LoggerFactory.getLogger(FutureUtil.class);

    public static <T> T getResult(Future<T> future) {
        T result = null;
        try {
            result = future.get();
        } catch (Exception e) {
            LoggerUtil.error(logger, e, e.getMessage());
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, e, e.getMessage());
        }

        return result;
    }

}