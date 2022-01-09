/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.model.User;

/**
 * @author Tony Zhao
 * @version $Id: ESCURDService.java, v 0.1 2022-01-09 8:42 PM Tony Zhao Exp $$
 */
public interface ESCURDService {

    /**
     * @param user 
     * @return
     */
    String addDocument(User user);

    /**
     * @return 
     */
    String aggregateQuery();

}
