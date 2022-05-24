/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Guava multiset utilize.
 * 
 * 可以用来计数的多值集合，不用重复写Map<String, Integer>来计算数字了。
 * 
 * @author Tony Zhao
 * @version $Id: MultiSetUtilize.java, v 0.1 2022-05-22 5:19 PM Tony Zhao Exp $$
 */
public class MultiSetUtilize {

    public static void main(String[] args) {
        Multiset<String> bookStore = HashMultiset.create();
        bookStore.add("Potter");
        bookStore.add("Potter");
        bookStore.add("Potter");

        System.out.println(bookStore.contains("Potter"));
        System.out.println(bookStore.count("Potter"));
    }

}