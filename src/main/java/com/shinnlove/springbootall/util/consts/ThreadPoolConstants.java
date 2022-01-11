/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.consts;

/**
 * @author Tony Zhao
 * @version $Id: ThreadPoolConstants.java, v 0.1 2021-08-12 2:49 PM Tony Zhao Exp $$
 */
public class ThreadPoolConstants {

    private ThreadPoolConstants() {

    }

    /** xxl job thread pool */
    public final static String XXL_JOB_THREAD_POOL_NAME          = "xxl-job-execute-pool";
    public final static int    XXL_JOB_THREAD_POOL_NUM           = 50;

    /** tag consume pool */
    public final static String TAG_CONSUME_POOL_NAME             = "tag-consume-pool";
    public final static int    TAG_CONSUME_POOL_NUM              = 50;

    /** status machine pool */
    public final static String MACHINE_THREAD_POOL_NAME          = "machine-execute-pool";
    public final static int    MACHINE_THREAD_POOL_NUM           = 50;

    /** notify dispatch pool */
    public final static String NOTIFY_THREAD_POOL_NAME           = "notify-execute-pool";
    public final static int    NOTIFY_THREAD_POOL_NUM            = 100;

    public final static String OFFICIAL_MESSAGE_THREAD_POOL_NAME = "official-message-execute-pool";
    public final static int    OFFICIAL_MESSAGE_THREAD_POOL_NUM  = 30;

    /** mail thread pool */
    public final static String MAIL_THREAD_POOL_NAME             = "mail-execute-pool";
    public final static int    MAIL_THREAD_POOL_CORE_NUM         = 10;
    public final static int    MAIL_THREAD_POOL_MAX_NUM          = 10;
    public final static int    MAIL_THREAD_POOL_QUEUE_SIZE       = 50;

}