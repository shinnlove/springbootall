package com.shinnlove.springbootall.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCallbackNotifyMessageLogEntity implements Serializable {

    private Long id;

    private String queueName;

    private String messageBody;

    private Integer isHandled;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

}