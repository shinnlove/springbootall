/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Arrays;
import java.util.Iterator;

import com.google.common.base.Splitter;

/**
 * Guava字符串分隔工具。
 * 
 * @author Tony Zhao
 * @version $Id: SplitterUtilize.java, v 0.1 2022-05-22 2:34 PM Tony Zhao Exp $$
 */
public class SplitterUtilize {

    public static void main(String[] args) {
        String sentence = "the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog.";

        // 普通的分隔
        simpleSplit(sentence);

        // 使用的guava分隔，在需要去除空字符、忽略全空字符串时显得很给力
        guavaSplit(sentence);
    }

    public static void simpleSplit(String sentence) {
        // 特别注意，传入的是字符串，只有逗号不需要反斜杠，竖线是需要反斜杠的
        String[] s = sentence.split(",");
        System.out.println(Arrays.asList(s));
    }

    public static void guavaSplit(String sentence) {
        // 特别注意：分隔的时候传入的是字符、不是字符串
        // 其次要注意返回的是个Iterable、并不是集合或List、Iterable是Collection的父接口、更base一些
        Iterable<String> words = Splitter.on(',')
            // 去除空格
            .trimResults()
            // 全部空字符不要
            .omitEmptyStrings()
            // 按分隔符切割
            .split(sentence);
        System.out.println(words);

        // 迭代稍微麻烦一点
        Iterator it = words.iterator();
        while (it.hasNext()) {
            System.out.println("each word:" + it.next());
        }
    }

}