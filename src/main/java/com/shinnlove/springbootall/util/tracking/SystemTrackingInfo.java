/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.tracking;

import java.io.Serializable;

/**
 * Service invoke tracking domain model.
 *
 * @author Tony Zhao
 * @version $Id: SystemTrackingInfo.java, v 0.1 2021-08-02 9:14 PM Tony Zhao Exp $$
 */
public class SystemTrackingInfo implements Serializable {

    private static final long serialVersionUID = -7121162767557800156L;

    private String            serviceUUID;
    private long              startTime;
    private long              duration;

    public SystemTrackingInfo() {
    }

    public SystemTrackingInfo(String serviceUUID) {
        this.serviceUUID = serviceUUID;
    }

    public SystemTrackingInfo(String serviceUUID, long startTime) {
        this.serviceUUID = serviceUUID;
        this.startTime = startTime;
    }

    public SystemTrackingInfo(String serviceUUID, long startTime, long duration) {
        this.serviceUUID = serviceUUID;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getServiceUUID() {
        return serviceUUID;
    }

    public void setServiceUUID(String serviceUUID) {
        this.serviceUUID = serviceUUID;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

}