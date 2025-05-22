package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DafengMemberMsgQueueLogEntity implements Serializable {

    private Long id;

    private Date createTime;

    private String queueName;

    private String messageBody;

    private static final long serialVersionUID = 1L;

}