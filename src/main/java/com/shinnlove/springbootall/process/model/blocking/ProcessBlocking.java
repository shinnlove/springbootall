/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.blocking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Process blocking core model refer to Po model.
 *
 * @author Tony Zhao
 * @version $Id: ProcessBlocking.java, v 0.1 2021-07-14 3:19 PM Tony Zhao Exp $$
 */
public class ProcessBlocking implements Serializable {

    private static final long serialVersionUID = -794873092947149526L;

    private Long              id;

    /** 主流程No. */
    private Long              mainProcessNo;

    /** 阻塞流程No. */
    private Long              obstacleByProcessNo;

    /** 主流程需阻塞的状态 */
    private Integer           refStatus;

    /** 备注 */
    private String            remark;

    public ProcessBlocking() {
    }

    public ProcessBlocking(Long id, Long mainProcessNo, Long obstacleByProcessNo, Integer refStatus,
                           String remark) {
        this.id = id;
        this.mainProcessNo = mainProcessNo;
        this.obstacleByProcessNo = obstacleByProcessNo;
        this.refStatus = refStatus;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainProcessNo() {
        return mainProcessNo;
    }

    public void setMainProcessNo(Long mainProcessNo) {
        this.mainProcessNo = mainProcessNo;
    }

    public Long getObstacleByProcessNo() {
        return obstacleByProcessNo;
    }

    public void setObstacleByProcessNo(Long obstacleByProcessNo) {
        this.obstacleByProcessNo = obstacleByProcessNo;
    }

    public Integer getRefStatus() {
        return refStatus;
    }

    public void setRefStatus(Integer refStatus) {
        this.refStatus = refStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}