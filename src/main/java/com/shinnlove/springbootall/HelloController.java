/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: HelloController.java, v 0.1 2022-01-08 7:23 PM Tony Zhao Exp $$
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return hello("world");
    }

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name) {
        logger.info("This is a info log record created by tony.");
        logger.warn("This is a warn log record created by tony.");
        logger.error("Create an error log record by tony.", new RuntimeException("Manually made runtime error happen."));
        return "Hello " + name + ", the second version.";
    }

}