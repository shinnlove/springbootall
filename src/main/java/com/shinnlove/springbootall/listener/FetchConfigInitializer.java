/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

import com.shinnlove.springbootall.conf.RemoteConfFile;
import com.shinnlove.springbootall.util.TonyPropertyEnvSource;

/**
 * @author Tony Zhao
 * @version $Id: FetchConfigInitializer.java, v 0.1 2022-05-27 3:10 PM Tony Zhao Exp $$
 */
public class FetchConfigInitializer implements GenericApplicationListener {

    private static final Class<?>[] EVENT_TYPES = { ApplicationEnvironmentPreparedEvent.class,
                                                    ApplicationPreparedEvent.class,
                                                    ContextClosedEvent.class,
                                                    ApplicationFailedEvent.class,
                                                    ApplicationReadyEvent.class };

    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return isAssignableFrom(eventType.getRawClass(), EVENT_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
        }
    }

    private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        // 原来系统环境变量
        ConfigurableEnvironment environment = event.getEnvironment();

        // new 一份file
        RemoteConfFile confFile = new RemoteConfFile();

        // 添加到环境变量里
        TonyPropertyEnvSource.addToEnvironment(environment, confFile);
    }

}