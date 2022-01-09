/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.springbootall.model.User;
import com.shinnlove.springbootall.service.ESCURDService;

/**
 * ES high rest client controller for query elasticsearch.
 * 
 * @author Tony Zhao
 * @version $Id: HighRestClientController.java, v 0.1 2022-01-09 8:13 PM Tony Zhao Exp $$
 */
@RestController
@RequestMapping(value = "/es")
public class HighRestClientController {

    @Qualifier("esCURDService")
    @Autowired
    private ESCURDService esCURDService;

    /**
     * Query index in terminal:
     * 
     * curl -XGET 'http://127.0.0.1:9200/users/_doc/_search?pretty=true'
     * 
     * @return      the doc index in elasticsearch.
     */
    @RequestMapping(value = "/add_index")
    public String addIndex() {
        User user = new User("tony", "男", 25);
        return esCURDService.addDocument(user);
    }

    /**
     * Complex aggregation query in elasticsearch.
     * 
     * @return 
     */
    @RequestMapping(value = "/agg_search")
    public String queryIndex() {
        return esCURDService.aggregateQuery();
    }

}