/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Map;
import java.util.Properties;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

import com.shinnlove.springbootall.conf.RemoteConf;

/**
 * @author Tony Zhao
 * @version $Id: TonyPropertySource.java, v 0.1 2022-05-27 2:49 PM Tony Zhao Exp $$
 */
public class TonyPropertySource extends EnumerablePropertySource<Object> {

    private static boolean      manually   = true;

    private volatile Properties properties = new Properties();

    private RemoteConf          remoteConf;

    public TonyPropertySource(String name, RemoteConf source) {
        super(name, new Object());
        remoteConf = source;
        load();
    }

    private void load() {
        String host = "www.tony-awesome.com";
        String port = "8888";
        String timeout = "99999";

        if (!manually) {
            // dynamic load env config
            host = remoteConf.getNullableString("redis.host");
            port = remoteConf.getNullableString("redis.port");
            timeout = remoteConf.getNullableString("redis.timeout");
        }

        // return config
        properties.put("redis.host", host);
        properties.put("redis.port", port);
        properties.put("redis.timeout", timeout);
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