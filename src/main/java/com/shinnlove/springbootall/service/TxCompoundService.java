/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

/**
 * @author Tony Zhao
 * @version $Id: TxCompoundService.java, v 0.1 2024-04-26 10:41 Tony Zhao Exp $$
 */
public interface TxCompoundService {

    Integer compound(String activityId, Long componentId, Long guid);

}
