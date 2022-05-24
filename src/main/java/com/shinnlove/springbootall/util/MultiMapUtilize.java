/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * 单个Key对应多个Value的工具。
 * 
 * @author Tony Zhao
 * @version $Id: MultiMapUtilize.java, v 0.1 2022-05-22 2:19 PM Tony Zhao Exp $$
 */
public class MultiMapUtilize {

    public static void main(String args[]) {

        MultiMapUtilize utilize = new MultiMapUtilize();

        Multimap<String, String> multimap = utilize.getMultimap();

        // get的是引用
        List<String> lowerList = (List<String>) multimap.get("lower");
        lowerList.remove("d");
        lowerList.add("f");
        // f元素还是添加进去了、d元素也被干掉了
        System.out.println("Modified lower case list=" + multimap);

        // 转成一个普通Map、但不能转成List<String>，因为是Collection..
        Map<String, Collection<String>> map = multimap.asMap();
        for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();

            // 元素还是集合元素，需要用迭代器来循环，稍有点不方便
            Collection<String> value = multimap.get("lower");
            Iterator<String> it = value.iterator();
            while (it.hasNext()) {
                System.out.println("Multimap as a map, key:value=" + key + ":" + it.next());
            }
        }

        // 像Map一样操作key集合
        System.out.println("Keys of Multimap");
        Set<String> keys = multimap.keySet();
        for (String key : keys) {
            System.out.println(key);
        }

        // 像Map一样所有的value集合
        System.out.println("Values of Multimap");
        Collection<String> values = multimap.values();
        System.out.println(values);
    }

    private Multimap<String, String> getMultimap() {
        //Map<String, List<String>>
        // lower -> a, b, c, d, e
        // upper -> A, B, C, D

        // 创建key为String, Value为List<String>的一个多值map
        Multimap<String, String> multimap = ArrayListMultimap.create();

        // 为key lower添加5个值
        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        // 为key upper添加4个值
        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");

        return multimap;
    }

}