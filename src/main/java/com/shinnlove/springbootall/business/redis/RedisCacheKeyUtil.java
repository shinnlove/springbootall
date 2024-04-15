/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.business.redis;

import com.shinnlove.springbootall.business.consts.Constant;

/**
 * @author Tony Zhao
 * @version $Id: RedisCacheKeyUtil.java, v 0.1 2024-04-15 11:15 Tony Zhao Exp $$
 */
public class RedisCacheKeyUtil {

    private static final String KEY_USER_PK_LEVEL_BUCKET_PREFIX =
            "act:" + Constant.IDENTIFIER + ":promotion-bucket-lv:";

    public static String getKeyUserPkLevelBucketPrefix() { return KEY_USER_PK_LEVEL_BUCKET_PREFIX; }

    public static String getUserPkBucketKey(int level) {
        return getKeyUserPkLevelBucketPrefix() + level;
    }

}