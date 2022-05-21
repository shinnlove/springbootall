/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import org.springframework.stereotype.Service;

import com.shinnlove.springbootall.service.SayHelloService;

/**
 * @author Tony Zhao
 * @version $Id: SayHelloServiceImpl.java, v 0.1 2022-05-16 4:10 PM Tony Zhao Exp $$
 */
@Service
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name + ", welcome to jaeger.";
    }

}