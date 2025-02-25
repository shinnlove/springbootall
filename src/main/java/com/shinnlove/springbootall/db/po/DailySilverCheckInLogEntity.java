package com.shinnlove.springbootall.db.po;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@Getter
@Setter
public class DailySilverCheckInLogEntity implements Serializable {
    private Long id;

    private String activityId;

    private Long componentId;

    private Long guid;

    private Integer dateMark;

    private Integer dayNumber;

    private String actionQueries;

    private String prizeIdList;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}