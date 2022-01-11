/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.tracking;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shinnlove.springbootall.util.log.LoggerUtil;

/**
 * Tracking thread local util for store service invoke info.
 *
 * @author Tony Zhao
 * @version $Id: TrackingUtil.java, v 0.1 2021-08-02 9:12 PM Tony Zhao Exp $$
 */
public class TrackingUtil {

    private static final Logger                    logger        = LoggerFactory
        .getLogger(TrackingUtil.class);

    private static ThreadLocal<SystemTrackingInfo> trackingLocal = new ThreadLocal<>();

    private TrackingUtil() {

    }

    public static void create() {
        long start = System.currentTimeMillis();
        String serviceUUID = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        SystemTrackingInfo trackingInfo = new SystemTrackingInfo(serviceUUID, start);
        set(trackingInfo);
    }

    public static void set(SystemTrackingInfo requestHeaderBaseInfo) {
        trackingLocal.set(requestHeaderBaseInfo);
    }

    public static SystemTrackingInfo get() {
        SystemTrackingInfo info = trackingLocal.get();
        if (info != null) {
            return info;
        }
        return null;
    }

    public static String getUUID() {
        String serviceUUID = "N/A";
        try {
            SystemTrackingInfo trackingInfo = get();
            if (trackingInfo != null) {
                serviceUUID = trackingInfo.getServiceUUID();
            }
        } catch (Exception e) {
            LoggerUtil.warn(logger, "Fetch service tracking failed, ex=", e.getMessage());
        }
        return serviceUUID;
    }

    public static String getOrCreateUUID() {
        String serviceUUID = "N/A";
        try {
            SystemTrackingInfo trackingInfo = get();
            if (trackingInfo != null) {
                serviceUUID = trackingInfo.getServiceUUID();
            } else {
                create();
                SystemTrackingInfo newTracking = get();
                serviceUUID = newTracking.getServiceUUID();
            }
        } catch (Exception e) {
            LoggerUtil.warn(logger, "Fetch service tracking failed, ex=", e.getMessage());
        }
        return serviceUUID;
    }

    public static SystemTrackingInfo getTracking() {
        SystemTrackingInfo tracking = null;
        try {
            tracking = get();
        } catch (Exception e) {
            LoggerUtil.warn(logger, "Fetch service tracking failed, ex=", e.getMessage());
        }
        return tracking;
    }

    public static SystemTrackingInfo getOrCreateTracking() {
        SystemTrackingInfo tracking = null;
        try {
            tracking = get();
            if (tracking == null) {
                create();
                tracking = get();
            }
        } catch (Exception e) {
            LoggerUtil.warn(logger, "Fetch service tracking failed, ex=", e.getMessage());
        }
        return tracking;
    }

    public static void remove() {
        trackingLocal.remove();
    }

}