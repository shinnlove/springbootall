/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import com.alibaba.fastjson.JSON;
import com.shinnlove.springbootall.models.TaskParams;
import com.shinnlove.springbootall.service.TaskScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tony Zhao
 * @version $Id: TaskController.java, v 0.1 2024-05-07 19:14 Tony Zhao Exp $$
 */
@RestController
public class TaskController {

    @Autowired
    private TaskScheduleService taskScheduleService;

    @RequestMapping(value = "/task_start", method = RequestMethod.GET)
    public Integer startTask(String name) {
        return taskScheduleService.startTask(name);
    }

    @RequestMapping(value = "/task_cancel", method = RequestMethod.GET)
    public Integer cancelTask(String name) {
        return taskScheduleService.cancelTask(name);
    }

    public static void main(String[] args) {
        String json = "{\"priority\":3,\"reload_balance_weight\":20}";
        TaskParams params = JSON.parseObject(json, TaskParams.class);
        System.out.println(params.getPriority() + ":" + params.getReloadBalanceWeight());
    }

}