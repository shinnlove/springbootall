/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Tony Zhao
 * @version $Id: UserPkGlobalStatEntity.java, v 0.1 2024-04-15 14:38 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPkGlobalStatEntity {

    private Long id;

    private String activityId;

    private Long guid;

    private Integer totalSuccessCount;

    private Integer totalFailureCount;

    private Integer totalDrawCount;

    private Integer promotionLevel;

    private Timestamp createTime;

    private Timestamp updateTime;

}