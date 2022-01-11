/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.common;

import com.shinnlove.springbootall.util.log.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: CommonUtil.java, v 0.1 2022-01-10 11:37 PM Tony Zhao Exp $$
 */
public class CommonUtil {

    private final static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static <T, S> List<T> copyList(List<S> s, Class<T> clazz) {
        if(CollectionUtils.isEmpty(s)) {
            return Collections.emptyList();
        }

        List<T> targets = new ArrayList<>(s.size());
        try {
            for(S source : s) {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(source, t);

                targets.add(t);
            }
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "type transf error!");
        }
        return targets;
    }

    public static <T, S> T copyObj(S s, Class<T> clazz) {
        if(s == null) {
            return null;
        }

        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(s, t);
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "type transf error!");
        }
        return t;
    }

    public static <T, S> T appendCopyObj(S s, T t) {
        if(s == null || t == null) {
            return null;
        }

        try {
            BeanUtils.copyProperties(s, t);
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "type transf error!");
        }
        return t;
    }

}