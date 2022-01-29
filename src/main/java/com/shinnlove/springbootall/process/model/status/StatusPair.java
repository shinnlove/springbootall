/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.model.status;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The source and destination pair of process.
 *
 * @author Tony Zhao
 * @version $Id: StatusPair.java, v 0.1 2021-07-26 9:14 PM Tony Zhao Exp $$
 */
public class StatusPair implements Serializable {

    private static final long serialVersionUID = -8472879298003178644L;

    /** -1 represents no limit from source */
    private int               sourceStatus     = -1;
    private int               destinationStatus;

    public StatusPair() {
    }

    public StatusPair(int sourceStatus, int destinationStatus) {
        this.sourceStatus = sourceStatus;
        this.destinationStatus = destinationStatus;
    }

    public int getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(int sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public int getDestinationStatus() {
        return destinationStatus;
    }

    public void setDestinationStatus(int destinationStatus) {
        this.destinationStatus = destinationStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}