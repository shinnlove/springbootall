package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthTicketSkuBindingEntity implements Serializable {

    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private String monthTicketId;

    private String monthTicketCardNo;

    private String stubId;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;

}