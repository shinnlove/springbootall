/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.condition.diy;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Tony Zhao
 * @version $Id: EncryptCondition.java, v 0.1 2022-01-09 2:55 PM Tony Zhao Exp $$
 */
public class EncryptCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 加入自定义的条件
        Resource res = context.getResourceLoader().getResource("salt.txt");
        Environment env = context.getEnvironment();
        return res.exists() && env.containsProperty("mobile.encrypt.enabled");
    }

}