/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.db.po;

import lombok.Data;

/**
 * @author Tony Zhao
 * @version $Id: SumAssistRecord.java, v 0.1 2024-08-19 16:44 Tony Zhao Exp $$
 */
@Data
public class SumAssistRecord {

    /** 总共未领取的积分 */
    private long totalUnreceivedPoints;

    /** 总共未领取的条数 */
    private long totalUnreceivedAssists;

}