/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.service.MobileEncryptService;

/**
 * @author Tony Zhao
 * @version $Id: MobileEncryptServiceImpl.java, v 0.1 2022-01-09 2:53 PM Tony Zhao Exp $$
 */
public class MobileEncryptServiceImpl implements MobileEncryptService {

    private static final String APP_KEY = "JHLJKASFGYHJSAF";

    @Override
    public String getAppKey() {
        return APP_KEY;
    }

}