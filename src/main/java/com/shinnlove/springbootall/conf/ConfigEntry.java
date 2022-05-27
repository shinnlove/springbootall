/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.conf;

/**
 * @author Tony Zhao
 * @version $Id: ConfigEntry.java, v 0.1 2022-05-27 3:20 PM Tony Zhao Exp $$
 */
public interface ConfigEntry {

    /**
     * Config key present in <a href="https://cloud.bilibili.co/">paladin</a>
     *
     * @return string config key
     */
    String getConfigKey();

    /**
     * Raw config value present in <a href="https://cloud.bilibili.co/">paladin</a>
     *
     * @return raw string value
     */
    String getRawValue();

}
