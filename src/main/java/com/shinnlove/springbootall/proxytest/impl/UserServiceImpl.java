/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.proxytest.impl;

import com.shinnlove.springbootall.proxytest.UserService;

/**
 * @author Tony Zhao
 * @version $Id: UserServiceImpl.java, v 0.1 2024-04-14 12:06 PM Tony Zhao Exp $$
 */
public class UserServiceImpl implements UserService {

    @Override
    public void speak() {
        System.out.println("Hello, it's me.");
    }

}