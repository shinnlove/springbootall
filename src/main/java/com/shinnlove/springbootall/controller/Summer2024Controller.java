/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: Summer2024Controller.java, v 0.1 2024-07-30 09:50 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/summer2024")
public class Summer2024Controller {

    @RequestMapping(value = "/say_hello")
    public String sayHello() {
        return "Hello, Summer 2024!";
    }

}