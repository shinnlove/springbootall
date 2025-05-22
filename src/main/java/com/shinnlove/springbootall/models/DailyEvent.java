/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tony Zhao
 * @version $Id: DailyEvent.java, v 0.1 2025-04-11 5:13 PM Tony Zhao Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyEvent {

    /** 主键id */
    private Long id;

    /** 日期格式 */
    private String dateFormatStr;

    /** 当天的事情 */
    private String eventName;

    /** 创建时间 */
    private long createTime;

    /** 更新时间 */
    private long updateTime;

}