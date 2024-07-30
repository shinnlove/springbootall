package com.shinnlove.springbootall.db.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ValidOrderMessageQueueLogEntity implements Serializable {

    private Long id;

    private String activityId;

    private Long componentId;

    private String queueName;

    private String messageBody;

    private String orderId;

    private Long guid;

    private Integer reconcileStatus;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}