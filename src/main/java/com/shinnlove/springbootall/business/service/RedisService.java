/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.business.service;

import com.shinnlove.springbootall.business.redis.RedisCacheKeyUtil;
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
 * @version $Id: RedisService.java, v 0.1 2024-04-15 11:17 Tony Zhao Exp $$
 */
public interface RedisService {

    Long redisInitLevel(Long guid, Integer startLevel);

    Long redisPromote(Long guid, Integer updateLevel);

    Long redisRandomOpponentGuid(long selfGuid, int level);

}