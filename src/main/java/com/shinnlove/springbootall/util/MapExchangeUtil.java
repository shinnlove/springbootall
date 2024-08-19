/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: MapExchangeUtil.java, v 0.1 2024-08-10 4:16 PM Tony Zhao Exp $$
 */
public class MapExchangeUtil {

    public static void main(String[] args) {
        // 原始数据结构 Map<Long, List<Long>>
        Map<Long, List<Long>> originalMap = new HashMap<>();
        originalMap.put(1L, Arrays.asList(10L, 20L, 30L));
        originalMap.put(2L, Arrays.asList(40L, 50L));
        originalMap.put(3L, Arrays.asList(60L, 70L, 80L, 90L));

        // 展平并且reverse
        Map<Long, Long> flattenedMap = originalMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(value -> new AbstractMap.SimpleEntry<>(entry.getKey(), value)))
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (e, r) -> e, LinkedHashMap::new));

        flattenedMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }

}