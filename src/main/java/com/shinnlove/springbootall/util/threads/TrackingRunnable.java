/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.threads;

import com.shinnlove.springbootall.util.tracking.SystemTrackingInfo;
import com.shinnlove.springbootall.util.tracking.TrackingUtil;

/**
 * @author Tony Zhao
 * @version $Id: TrackingRunnable.java, v 0.1 2021-08-03 8:38 PM Tony Zhao Exp $$
 */
public class TrackingRunnable implements Runnable {

    private Runnable           runnable;

    private SystemTrackingInfo trackingInfo;

    /**
     * Constructor for reflects.
     */
    public TrackingRunnable() {
    }

    public TrackingRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public TrackingRunnable(Runnable runnable, SystemTrackingInfo trackingInfo) {
        this.runnable = runnable;
        this.trackingInfo = trackingInfo;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public SystemTrackingInfo getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(SystemTrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    @Override
    public void run() {
        TrackingUtil.set(trackingInfo);
        runnable.run();
    }

}