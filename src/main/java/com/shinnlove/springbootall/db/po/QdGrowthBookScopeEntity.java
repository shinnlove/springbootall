package com.shinnlove.springbootall.db.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class QdGrowthBookScopeEntity implements Serializable {

    private Long id;

    private Long cxid;

    private String name;

    private Long libraryUserCount;

    /** 大数据侧字段：最近7天日均阅读数 */
    private Integer sevenDayReadUserCount;

    /** 大数据侧字段：最近7天日均订阅数 */
    private Integer sevenDaySubUserCount;

    /** 大数据侧字段：最近7天日均收入 */
    private BigDecimal sevenDaySubIncome;

    private Integer type;

    private Date createTime;

    private Long statisDay;

    private static final long serialVersionUID = 1L;

}