/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author Tony Zhao
 * @version $Id: UserPkDailyStatEntity.java, v 0.1 2024-04-15 14:35 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPkDailyStatEntity {

    private Long id;

    private String activityId;

    private Long guid;

    private Integer chanceCount;

    private Integer successCount;

    private Integer failureCount;

    private Integer drawCount;

    private Integer statDate;

    private Timestamp createTime;

    private Timestamp updateTime;

}