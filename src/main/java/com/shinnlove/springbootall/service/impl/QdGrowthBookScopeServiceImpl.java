/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service.impl;

import com.shinnlove.springbootall.db.dao.QdGrowthBookScopeRepo;
import com.shinnlove.springbootall.db.po.QdGrowthBookScopeEntity;
import com.shinnlove.springbootall.service.QdGrowthBookScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Tony Zhao
 * @version $Id: QdGrowthBookScopeServiceImpl.java, v 0.1 2025-01-10 08:53 Tony Zhao Exp $$
 */
@Service
public class QdGrowthBookScopeServiceImpl implements QdGrowthBookScopeService {

    @Autowired
    private QdGrowthBookScopeRepo qdGrowthBookScopeRepo;

    @Override
    public QdGrowthBookScopeEntity queryGrowthByCbid(long cbid) {
        QdGrowthBookScopeEntity entity = qdGrowthBookScopeRepo.queryGrowthByCbid(cbid);
        return Objects.nonNull(entity) ? entity : null;
    }

}