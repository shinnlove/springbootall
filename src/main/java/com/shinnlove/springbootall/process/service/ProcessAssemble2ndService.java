/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.service;

import java.io.InputStream;

/**
 * @author Tony Zhao
 * @version $Id: ProcessAssemble2ndService.java, v 0.1 2022-01-29 5:42 PM Tony Zhao Exp $$
 */
public interface ProcessAssemble2ndService {

    /**
     * Initialize process by xml files under classpath.
     * 
     * @param stream 
     */
    void initialize(InputStream stream);

}
