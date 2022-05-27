/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.conf;

/**
 * @author Tony Zhao
 * @version $Id: RemoteConfHolder.java, v 0.1 2022-05-27 3:54 PM Tony Zhao Exp $$
 */
public class RemoteConfHolder {

    public static void set(RemoteConf conf) {
        RemoteConfAdapter.conf = conf;
    }

    public static RemoteConf get() {
        return RemoteConfAdapter.conf;
    }

    private static class RemoteConfAdapter implements RemoteConf {

        static RemoteConf conf = new RemoteConfAdapter();

        @Override
        public String getNullableString(String key) {
            return "";
        }

    }

}