/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.es.dao.IFFOrderRepository;
import com.shinnlove.springbootall.es.model.IFFOrder;

/**
 * @author Tony Zhao
 * @version $Id: ESController.java, v 0.1 2022-01-09 7:25 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping("/iff")
public class ESController {

    @Autowired
    private IFFOrderRepository iffOrderRepository;

    @RequestMapping(value = "/addOrder", method = { RequestMethod.GET, RequestMethod.POST })
    public void addOrder() {
        IFFOrder iffOrder = new IFFOrder(1L, "100015000000001", "HDB", "EMSCX0000951356291717",
            "FUNDED", 0, 20d,
            "FREIGHT-100014149303656-90000400000000-fail-current purchase order is partial paid- need retry:Order funding retry for purchaseOrderId=90000400000000",
            "66666");
        iffOrderRepository.save(iffOrder);
    }

}