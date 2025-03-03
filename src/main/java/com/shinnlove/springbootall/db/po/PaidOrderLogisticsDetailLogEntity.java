package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaidOrderLogisticsDetailLogEntity implements Serializable {

    private Long id;

    private String expressNo;

    private Date time;

    private String context;

    private String status;

    private String city;

    private Integer logOrder;

    private Integer isDeleted;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;

}