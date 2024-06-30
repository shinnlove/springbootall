/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.ItemSystemDeductEntity;


/**
 * @author Tony Zhao
 * @version $Id: ItemSystemDeductService.java, v 0.1 2024-06-30 20:11 Tony Zhao Exp $$
 */
public interface ItemSystemDeductService {

    long addSystemDeduct(String activityId, Long componentId, Long itemId, Long guid,
                         Integer systemDeductPrice);

    ItemSystemDeductEntity queryByDeductId(String activityId, Long componentId, Long deductId);

}
