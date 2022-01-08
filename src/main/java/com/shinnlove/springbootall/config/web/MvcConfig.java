/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.web;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * add cors check, add interceptors, and extend jackson mapping in web mvc config.
 *
 * @author Tony Zhao
 * @version $Id: MvcConfig.java, v 0.1 2022-01-08 10:50 PM Tony Zhao Exp $$
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置跨域访问
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // add snake case jackson mapping here...
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper objectMapper = new ObjectMapper();
                // snake case with underscore
                objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                // ignore unknown fields
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                // date format
                objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));

                ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper);
            }
        }
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 配置格式化
    }

}