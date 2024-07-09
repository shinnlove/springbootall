/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;

/**
 * @author Tony Zhao
 * @version $Id: UserGuessAggEntity.java, v 0.1 2024-07-07 16:29 Tony Zhao Exp $$
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserGuessAggEntity implements Serializable {

    private String activityId;

    private Long cbid;

    private Integer guessRevealIdentity;

    private Integer guessNum;

    private static final long serialVersionUID = 1L;

}