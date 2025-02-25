/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.QdGrowthBookScopeEntity;

/**
 * @author Tony Zhao
 * @version $Id: QdGrowthBookScopeService.java, v 0.1 2025-01-10 08:53 Tony Zhao Exp $$
 */
public interface QdGrowthBookScopeService {

    /**
     * @param cbid
     * @return
     */
    QdGrowthBookScopeEntity queryGrowthByCbid(long cbid);

}
