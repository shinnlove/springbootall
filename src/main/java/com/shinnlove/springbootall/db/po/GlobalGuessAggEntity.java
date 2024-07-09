/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony Zhao
 * @version $Id: GlobalGuessAggEntity.java, v 0.1 2024-07-07 16:56 Tony Zhao Exp $$
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class GlobalGuessAggEntity implements Serializable {

    private String activityId;

    private long cbid;

    private Integer guessRevealIdentity;

    private Long guessAuthorId;

    private String guessAuthorName;

    private Integer guessNum;

    private Date minCreateTime;

    private static final long serialVersionUID = 1L;

}