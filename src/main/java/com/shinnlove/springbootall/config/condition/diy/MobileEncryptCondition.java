/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.condition.diy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.shinnlove.springbootall.service.MobileEncryptService;
import com.shinnlove.springbootall.service.impl.MobileEncryptServiceImpl;

/**
 * @author Tony Zhao
 * @version $Id: MobileEncryptCondition.java, v 0.1 2022-01-09 2:50 PM Tony Zhao Exp $$
 */
@Configuration
public class MobileEncryptCondition {

    @Bean
    @Conditional(EncryptCondition.class)
    public MobileEncryptService createEncryptService() {
        return new MobileEncryptServiceImpl();
    }

}