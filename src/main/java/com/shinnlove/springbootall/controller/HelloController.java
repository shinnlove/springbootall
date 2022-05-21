/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.service.SayHelloService;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

/**
 * @author Tony Zhao
 * @version $Id: HelloController.java, v 0.1 2022-05-16 4:05 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private SayHelloService sayHelloService;

    @RequestMapping(value = "/greet/{name}")
    public String sayHello(@PathVariable("name") String name) {

        Tracer tracer = GlobalTracer.get();
        // 创建Span

        Span span = tracer.buildSpan("Service Call").withTag("myTag", "tonyFirstSpan").start();

        span.log("call say hello service");

        tracer.scopeManager().activate(span);
        tracer.activeSpan().setTag("methodName", "tonyTestTracing");

        // 业务逻辑

        String result = sayHelloService.sayHello(name);

        span.finish();

        return result;
    }

}