/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.process.enums.ActionType;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.no.SnowflakeIdWorker;
import com.shinnlove.springbootall.process.service.StatusMachineService;

/**
 * Test status machine controller.
 *
 * @author Tony Zhao
 * @version $Id: StatusMachineController.java, v 0.1 2022-01-24 6:39 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping("/status")
public class StatusMachineController {

    @Autowired
    private StatusMachineService statusMachineService;

    @Autowired
    private SnowflakeIdWorker    snowflakeIdWorker;

    @RequestMapping("/proceed")
    public String proceedProcess(String name) {

        int actionId = ActionType.AUTO_ONLINE.getActionId();
        long refUniqueNo = snowflakeIdWorker.nextId();
        String info = "this is remark";
        DataContext<String> dataContext = new DataContext<>(info);

        statusMachineService.proceedProcess(actionId, refUniqueNo, dataContext);

        return "hello " + name;
    }

}