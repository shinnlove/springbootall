/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.List;
import java.util.StringJoiner;

import com.google.common.primitives.Longs;

/**
 * Longs的用法，和Ints一样。
 * 
 * @author Tony Zhao
 * @version $Id: LongsUtilize.java, v 0.1 2022-05-22 4:11 PM Tony Zhao Exp $$
 */
public class LongsUtilize {

    public static void main(String[] args) {
        // Longs类的用法
        testLongs();
    }

    public static void testLongs() {
        long[] longArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        //convert array of primitives to array of objects
        // 直接转成List集合
        List<Long> listArray = Longs.asList(longArray);
        System.out.println(listArray.toString());

        //convert array of objects to array of primitives
        // list to array
        long[] convertIntArray = Longs.toArray(listArray);
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < convertIntArray.length; i++) {
            joiner.add(String.valueOf(convertIntArray[i]));
        }
        System.out.println("[" + joiner + "]");

        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Longs.contains(longArray, 5));

        //Returns the minimum		
        System.out.println("Min: " + Longs.min(longArray));

        //Returns the maximum		
        System.out.println("Max: " + Longs.max(longArray));

        //get the byte array from an integer
        byte[] byteArray = Longs.toByteArray(20000);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
    }

}