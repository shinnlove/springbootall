/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.process.enums.ActionType;
import com.shinnlove.springbootall.process.enums.TemplateType;
import com.shinnlove.springbootall.process.model.context.DataContext;
import com.shinnlove.springbootall.process.no.SnowflakeIdWorker;
import com.shinnlove.springbootall.process.service.StatusMachineService;
import com.shinnlove.springbootall.service.biz.revise.RevisePriceService;

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

    @Autowired
    private RevisePriceService   revisePriceService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initProcess() {
        int id = TemplateType.ORDER_PRICE.getTemplateId();
        BigDecimal before = new BigDecimal(127.00);
        BigDecimal after = new BigDecimal(315.00);

        long result = revisePriceService.submitRevise(id, before, after, "tony");

        return String.valueOf(result);
    }

    @RequestMapping("/proceed")
    public String proceedProcess(String name) {

        int actionId = ActionType.EXPENSE_REVISE_REJECT.getActionId();
        long refUniqueNo = snowflakeIdWorker.nextId();
        String info = "this is remark";
        DataContext<String> dataContext = new DataContext<>(info);

        statusMachineService.proceedProcess(actionId, refUniqueNo, dataContext);

        return "hello " + name;
    }

}