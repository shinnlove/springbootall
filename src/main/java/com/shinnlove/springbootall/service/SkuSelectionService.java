/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.SkuSelectionEntity;

import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: SkuSelectionService.java, v 0.1 2024-06-30 20:59 Tony Zhao Exp $$
 */
public interface SkuSelectionService {

    long addItemSelection(String activityId, Long componentId, Long guid, Long itemId,
                                 String itemNameSnapshot, String itemDescSnapshot, String itemImgUrlSnapshot,
                                 int priceSnapshot, int lowestPriceSnapshot, long ddl1stTime, Integer payStatus);

    int updateSelectionPayStatus(String activityId, long componentId, long selectId,
                                        int payStatus);

    int updateSelectionValidStatus(String activityId, long componentId, long selectId,
                                          int validStatus);

    int updateSelection2ndStartEndTime(String activityId, long componentId, long selectId, long start2ndTime,
                                              long ddl2ndTime);

    SkuSelectionEntity queryCurrentSelection(String activityId, long componentId, long guid);

    SkuSelectionEntity queryBySelectId(String activityId, long componentId, long selectId);

    SkuSelectionEntity querySelectByIdForUpdate(String activityId, long componentId, long selectId);

}
