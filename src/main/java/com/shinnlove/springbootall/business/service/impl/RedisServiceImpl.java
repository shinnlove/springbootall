/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.business.service.impl;

import com.shinnlove.springbootall.business.redis.RedisCacheKeyUtil;
import com.shinnlove.springbootall.business.service.RedisService;
import com.shinnlove.springbootall.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: RedisServiceImpl.java, v 0.1 2024-04-15 11:24 Tony Zhao Exp $$
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisManager redisManager;

    public Long redisInitLevel(Long guid, Integer startLevel) {
        if (Objects.isNull(guid) || guid <= 0L) {
            return 0L;
        }

        final String levelKey = RedisCacheKeyUtil.getUserPkBucketKey(startLevel);
        return redisManager.execute(jedis -> jedis.sadd(levelKey, String.valueOf(guid)));
    }

    public Long redisPromote(Long guid, Integer updateLevel) {
        if (updateLevel < 2) {
            // no zero, and default is 1
            return 0L;
        }

        final String currentLevelKey = RedisCacheKeyUtil.getUserPkBucketKey(updateLevel - 1);
        final String nextLevelKey = RedisCacheKeyUtil.getUserPkBucketKey(updateLevel);

        return redisManager
                .execute(jedis -> jedis.smove(currentLevelKey, nextLevelKey, String.valueOf(guid)));
    }

    public Long redisRandomOpponentGuid(long selfGuid, int level) {
        List<Long> result = redisRandomItem(selfGuid, level, level);
        return CollectionUtils.isEmpty(result) ? 0L : result.get(0);
    }

    private List<Long> redisRandomItem(Long selfGuid, int startLevel, int currentLevel) {
        if (currentLevel == 0) {
            return Collections.emptyList();
        }

        // self level random two item because redis don't have de-dup, might get self's id
        int count = currentLevel < startLevel ? 1 : 2;

        final String levelKey = RedisCacheKeyUtil.getUserPkBucketKey(currentLevel);
        List<String> result = redisManager.execute(jedis -> jedis.srandmember(levelKey, count));

        if (CollectionUtils.isEmpty(result)) {
            return redisRandomItem(selfGuid, startLevel, --currentLevel);
        }

        List<Long> distinct = result.stream()
                .map(Long::parseLong).filter(r -> !r.equals(selfGuid)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(distinct)) {
            return redisRandomItem(selfGuid, startLevel, --currentLevel);
        }

        return distinct;
    }

}