/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.model.Student;
import com.shinnlove.springbootall.model.Teacher;

/**
 * @author Tony Zhao
 * @version $Id: HelloController.java, v 0.1 2022-03-14 9:59 AM Tony Zhao Exp $$
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Teacher sayHello() {

        long no = 605428980236943362L;
        Student s = new Student(no, "tony", 1);
        Teacher t = new Teacher(String.valueOf(no), "evelyn", 2);

        return t;
    }

}