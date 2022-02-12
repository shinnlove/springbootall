/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.process.enums.ActionType;
import com.shinnlove.springbootall.process.enums.TemplateType;
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
    private RevisePriceService revisePriceService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initProcess() {
        int id = TemplateType.ORDER_PRICE.getTemplateId();
        BigDecimal before = new BigDecimal(127.00);
        BigDecimal after = new BigDecimal(315.00);

        long result = revisePriceService.submitRevise(id, before, after, "tony");

        return String.valueOf(result);
    }

    @RequestMapping("/proceed/{id}")
    public String proceedProcess(@PathVariable(value = "id") long refUniqueNo) {
        int actionId = ActionType.ORDER_PENDING_CONFIRM.getActionId();

        long result = revisePriceService.auditRevise(actionId, refUniqueNo, 1, "Tony酱");

        return String.valueOf(result);
    }

    @RequestMapping("/pipeline")
    public String pipelineProcess() {
        int actionId = ActionType.ORDER_PENDING_CONFIRM.getActionId();

        long result = revisePriceService.pipelineAudit(actionId);

        return String.valueOf(result);
    }

}