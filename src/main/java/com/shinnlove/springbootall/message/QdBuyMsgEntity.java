/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.message;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: QdBuyMsgEntity.java, v 0.1 2024-07-30 10:26 Tony Zhao Exp $$
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class QdBuyMsgEntity implements Serializable {

    private String orderId;

    private long guid;

    private String eventType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) {

        Map<Long, List<Long>> map = new HashMap<>();

        map.put(123L, Arrays.asList(1L, 2L, 3L, 4L, 5L));
        map.put(234L, Arrays.asList(6L, 7L, 8L, 9L, 10L));

        Map<Long, Long> collect = map.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .collect(Collectors.toMap(Function.identity(), e -> entry.getKey()))
                        .entrySet()
                        .stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e, r) -> e, LinkedHashMap::new));

        System.out.println(collect);
    }

}