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
 * @version $Id: UserPkRecordEntity.java, v 0.1 2024-04-15 14:41 Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPkRecordEntity {

    private Long id;

    private String activityId;

    private Integer itemType;

    private Long challengerGuid;

    private Integer challengerItemNum;

    private Long defenderGuid;

    private Integer defenderItemNum;

    private Integer pkStatus;

    private String prizeName;

    private String prizeDesc;

    private String prizeImgUrl;

    private Timestamp createTime;

    private Timestamp updateTime;

}