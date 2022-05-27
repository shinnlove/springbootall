/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import com.shinnlove.springbootall.conf.RemoteConf;

/**
 * @author Tony Zhao
 * @version $Id: TonyPropertyEnvSource.java, v 0.1 2022-05-27 3:15 PM Tony Zhao Exp $$
 */
public class TonyPropertyEnvSource extends PropertySource<RemoteConf> {

    private static final Logger LOGGER                     = LoggerFactory
        .getLogger(TonyPropertyEnvSource.class);

    public static final String  TONY_PROPERTY_SOURCES_NAME = "TonyFilePropertySource";

    public TonyPropertyEnvSource(RemoteConf source) {
        super(TONY_PROPERTY_SOURCES_NAME, source);
    }

    public TonyPropertyEnvSource(String name, RemoteConf source) {
        super(TONY_PROPERTY_SOURCES_NAME, source);
    }

    @Override
    public Object getProperty(String name) {
        return source.getNullableString(name);
    }

    public static void addToEnvironment(ConfigurableEnvironment environment,
                                        RemoteConf configSource) {
        // 所有的环境变量
        MutablePropertySources sources = environment.getPropertySources();

        // 自定义的当前的变量
        PropertySource<?> existing = sources.get(TONY_PROPERTY_SOURCES_NAME);

        if (Objects.nonNull(existing)) {
            LOGGER.info("{} already present", TONY_PROPERTY_SOURCES_NAME);
            return;
        }

        // 新建一个环境变量source
        TonyPropertyEnvSource paladinPropertySource = new TonyPropertyEnvSource(configSource);

        // 追加到环境变量里
        sources.addLast(paladinPropertySource);

        LOGGER.info("{} add to Environment", TONY_PROPERTY_SOURCES_NAME);
    }
}
