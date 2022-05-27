/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bilibili.config.ReloadTask;
import com.bilibili.config.Listener.Listeners;
import com.bilibili.config.entity.ConfVersion;
import com.bilibili.config.entity.SysConfig;
import com.bilibili.config.http.FetcherConfig;
import com.bilibili.config.utils.SysConfigUtil;

/**
 * @author Tony Zhao
 * @version $Id: RemoteConfFile.java, v 0.1 2022-05-27 3:25 PM Tony Zhao Exp $$
 */
public class RemoteConfFile implements RemoteConf {

    private static final Logger logger  = LoggerFactory.getLogger(RemoteConfFile.class);

    private Map<String, String> configs = new HashMap<>();

    public RemoteConfFile() {
        try {
            init();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String getNullableString(String key) {
        return Optional.ofNullable(configs.get(key)).orElse(null);
    }

    private void init() throws Exception {
        // do http client

        SysConfigUtil.initProperties();

        for (int i = 0; i < 3; ++i) {
            try {
                ConfVersion ver = FetcherConfig.check(-1);
                if (ver.getVersion() == -1) {
                    Thread.sleep(1000L);
                } else {
                    String conf = FetcherConfig.getFile(ver.getVersion());
                    if (!StringUtils.isBlank(conf) && FetcherConfig.writeFile(conf)) {
                        SysConfig.getInstance().setVersion(ver.getVersion());
                        SysConfig.getInstance().setDiffs(ver.getDiffs());
                        Listeners.sendAfterConfig();
                        if (SysConfig.getInstance().getReload()) {
                            ReloadTask.startTask();
                            return;
                        }

                        return;
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        // 假设文件里是这样的内容
        //        configs.put("redis.host", "www.google.com");
        //        configs.put("redis.port", "6666");
        //        configs.put("redis.timeout", "12345");
    }

}