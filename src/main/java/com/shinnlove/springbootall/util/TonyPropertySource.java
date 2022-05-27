/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Map;
import java.util.Properties;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

/**
 * @author Tony Zhao
 * @version $Id: TonyPropertySource.java, v 0.1 2022-05-27 2:49 PM Tony Zhao Exp $$
 */
public class TonyPropertySource extends EnumerablePropertySource<Object> {

    private volatile Properties properties = new Properties();

    public TonyPropertySource(String name, Object source) {
        super(name, new Object());
        load();
    }

    private void load() {
        properties.put("redis.host", "www.tony-awesome.com");
        properties.put("redis.port", "8888");
        properties.put("redis.timeout", "99999");
    }

    @Override
    public Object getProperty(String name) {
        return properties.getProperty(name);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(((Map) properties).keySet());
    }

}