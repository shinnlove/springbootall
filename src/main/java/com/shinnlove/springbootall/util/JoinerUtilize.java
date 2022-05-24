/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import com.google.common.base.Joiner;

/**
 * jdk和Guava字符串连接。
 * 
 * @author Tony Zhao
 * @version $Id: JoinerUtilize.java, v 0.1 2022-05-22 2:48 PM Tony Zhao Exp $$
 */
public class JoinerUtilize {

    public static void main(String[] args) {
        // jdk自带的连接：需要自己过滤连接，只能固定字符分隔
        jdkJoiner(1, 2, 3, 4, 5, null, 6);

        // guava的连接
        guavaJoiner();
    }

    public static void jdkJoiner(Object... args) {
        // JDK自带的分隔符也可以接受任意字符
        StringJoiner joiner = new StringJoiner("呵呵");
        for (Object arg : args) {
            // 这里需要自己过滤null元素
            joiner.add(String.valueOf(arg));
        }

        // 最后的结果直接toString
        System.out.println(joiner.toString());
    }

    public static void guavaJoiner() {
        // 使用guava的连接器，只要直接asList就可以，元素也是泛型的
        List<Object> elements = Arrays.asList(1, 2, 3, 4, 5, null, 6);
        // 分隔符和JDK的分隔符一样
        String result = Joiner.on("哈哈")
            // 过滤非空元素
            .skipNulls()
            // 再连接可迭代元素
            .join(elements);

        System.out.println(result);
    }

}