/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: GmVoteController.java, v 0.1 2024-09-02 19:23 Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/gm_dao")
public class GmVoteController {

    private static Logger logger = LoggerFactory.getLogger(GmVoteController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world.";
    }

}