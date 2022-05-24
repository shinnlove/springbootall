/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.primitives.Ints;

/**
 * Guava Ints类的各种用法。
 * 
 * @author Tony Zhao
 * @version $Id: IntsUtilize.java, v 0.1 2022-05-22 3:24 PM Tony Zhao Exp $$
 */
public class IntsUtilize {

    public static void main(String[] args) {
        // ints类的用法
        testInts();
    }

    public static void testInts() {
        int[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        //convert array of primitives to array of objects
        // 直接转成List集合
        List<Integer> listArray = Ints.asList(intArray);
        System.out.println(listArray.toString());

        //convert array of objects to array of primitives
        // list to array
        int[] convertIntArray = Ints.toArray(listArray);
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < convertIntArray.length; i++) {
            joiner.add(String.valueOf(convertIntArray[i]));
        }
        System.out.println("[" + joiner + "]");

        // 为什么这里是小括号？
        System.out.println(ArrayUtils.toString(convertIntArray));

        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Ints.contains(intArray, 5));

        //Returns the minimum		
        System.out.println("Min: " + Ints.min(intArray));

        //Returns the maximum		
        System.out.println("Max: " + Ints.max(intArray));

        //get the byte array from an integer
        byte[] byteArray = Ints.toByteArray(20000);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
    }

}