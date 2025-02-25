/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.service;

import com.shinnlove.springbootall.db.po.BookAdjustReasonMessageEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: BookAdjustReasonService.java, v 0.1 2025-01-13 17:02 Tony Zhao Exp $$
 */
public interface BookAdjustReasonService {

    long insertSelective();

    int updateBookCurrentCheckLevelAndAuditStatus(long id, int checkLevel, String auditStatus);

    int updateBookAdjustReasonPushResult(long id);

    List<BookAdjustReasonMessageEntity> queryMessageByCbid(long cbid);

}
