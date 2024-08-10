/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author Tony Zhao
 * @version $Id: UserBandLogExtEntity.java, v 0.1 2024-06-04 19:48 Tony Zhao Exp $$
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserBandLogExtEntity {

    private String activityId;

    private Long guid;

    private Integer promotionLevel;

    private Timestamp latestPromotionTime;

}