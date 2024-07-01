/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.SkuSelectionEntity;

/**
 * @author Tony Zhao
 * @version $Id: TestSkuSelectionService.java, v 0.1 2024-07-01 14:17 Tony Zhao Exp $$
 */
public interface TestSkuSelectionService {

    long addItemSelection();

    int updateSelectionPayStatus();

    int updateSelectionValidStatus();

    int updateSelection2ndStartEndTime();

    SkuSelectionEntity queryCurrentSelection();

    SkuSelectionEntity queryBySelectId();

    SkuSelectionEntity querySelectByIdForUpdate();

}
