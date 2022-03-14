/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author Tony Zhao
 * @version $Id: WebConfig.java, v 0.1 2022-03-14 10:01 AM Tony Zhao Exp $$
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper objectMapper = new ObjectMapper();
                // snake case with underscore
                objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                // ignore unknown fields
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                // date format
                objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));

                // VIP: long类型转String解决精度丢失问题(但是timestamp有问题)
                //                SimpleModule module = new SimpleModule();
                //                module.addSerializer(Long.class, ToStringSerializer.instance);
                //                module.addSerializer(Long.TYPE, ToStringSerializer.instance);
                //                objectMapper.registerModule(module);

                ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper);
            }
        }
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

}