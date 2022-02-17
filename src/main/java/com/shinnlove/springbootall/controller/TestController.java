/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.util.common.AssertUtil;

/**
 * @author Tony Zhao
 * @version $Id: TestController.java, v 0.1 2022-02-17 4:08 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping("/bean/{name}")
    public String pipelineProcess(@PathVariable(value = "name") String beanId) {

        Object r = context.getBean(beanId);

        AssertUtil.isNotNull(r);

        return "get";
    }

}