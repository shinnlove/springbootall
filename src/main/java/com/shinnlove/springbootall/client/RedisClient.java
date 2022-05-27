/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.client;

/**
 * @author Tony Zhao
 * @version $Id: RedisClient.java, v 0.1 2022-05-27 2:44 PM Tony Zhao Exp $$
 */
public class RedisClient {

    private String host;
    private int    port;
    private long   timeout;

    public RedisClient(String host, int port, long timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public String readConfig() {
        return "host=" + host + ", port=" + port + ", timeout=" + timeout;
    }

}