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

import com.alibaba.fastjson.JSON;
import com.shinnlove.springbootall.process.enums.ActionType;
import com.shinnlove.springbootall.process.enums.TemplateType;
import com.shinnlove.springbootall.service.biz.revise.RevisePriceService;
import com.shinnlove.springbootall.service.revise.model.UpperReviseInfo;
import com.shinnlove.springbootall.service.revise.service.ReviseService;

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

    @Autowired
    private ReviseService      reviseService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initProcess() {
        int id = TemplateType.ORDER_PRICE.getTemplateId();
        BigDecimal before = new BigDecimal(127.00);
        BigDecimal after = new BigDecimal(315.00);

        long result = revisePriceService.submitRevise(id, before, after, "tony");

        return String.valueOf(result);
    }

    @RequestMapping(value = "/submit_revise", method = RequestMethod.GET)
    public String initRevise(UpperReviseInfo reviseInfo) {
        long result = reviseService.submitRevise(reviseInfo);

        // return parent ref unique no
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/parent", method = RequestMethod.GET)
    public String initParentProcess() {
        BigDecimal before = new BigDecimal(127.00);
        BigDecimal after = new BigDecimal(315.00);

        long parentRefNo = revisePriceService.submitMultipleRevise(before, after, "Tony");

        return String.valueOf(parentRefNo);
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

        Object result = revisePriceService.pipelineAudit(actionId);

        return JSON.toJSONString(result);
    }

}