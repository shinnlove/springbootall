/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.config.condition.property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Tony Zhao
 * @version $Id: MessageAutoConfig.java, v 0.1 2022-01-09 2:50 PM Tony Zhao Exp $$
 */
@ConditionalOnProperty(name = "message.center.enabled", havingValue = "true", matchIfMissing = true)
public class MessageAutoConfig {
}