/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Apache common collection的交叉并补，非常的好用。
 * 
 * 先通过Arrays.asList转换成集合，
 * 然后通过union, intersection, disjunction, subtract四个操作来简化集合。
 * 
 * @author Tony Zhao
 * @version $Id: ApacheCollectionUtilize.java, v 0.1 2022-05-22 6:00 PM Tony Zhao Exp $$
 */
public class ApacheCollectionUtilize {

    public static void main(String[] args) {
        // 测试两个集合的并集
        testUnion();

        // 测试两个集合的交集
        testIntersection();

        // 测试交集的补集（析取）
        testDisjunction();

        // 测试差集（扣除）
        testSubtract();
    }

    public static void testUnion() {
        String[] arrayA = new String[] { "A", "B", "C", "D", "E", "F" };
        String[] arrayB = new String[] { "B", "D", "F", "G", "H", "K" };
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);
        //2个数组取并集 
        System.out.println(ArrayUtils.toString(CollectionUtils.union(listA, listB)));
        //[A, B, C, D, E, F, G, H, K]
    }

    public static void testIntersection() {
        String[] arrayA = new String[] { "A", "B", "C", "D", "E", "F" };
        String[] arrayB = new String[] { "B", "D", "F", "G", "H", "K" };
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);
        //2个数组取交集 
        System.out.println(ArrayUtils.toString(CollectionUtils.intersection(listA, listB)));
        //[B, D, F]
    }

    public static void testDisjunction() {
        String[] arrayA = new String[] { "A", "B", "C", "D", "E", "F" };
        String[] arrayB = new String[] { "B", "D", "F", "G", "H", "K" };
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);
        //2个数组取交集 的补集
        System.out.println(ArrayUtils.toString(CollectionUtils.disjunction(listA, listB)));
        //[A, C, E, G, H, K]
    }

    public static void testSubtract() {
        String[] arrayA = new String[] { "A", "B", "C", "D", "E", "F" };
        String[] arrayB = new String[] { "B", "D", "F", "G", "H", "K" };
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);
        //arrayA扣除arrayB
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
        //[A, C, E]
    }

}