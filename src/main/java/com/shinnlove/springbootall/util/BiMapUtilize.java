/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.shinnlove.springbootall.enums.OperatorType;

/**
 * Guava的BiMap使用。
 * 
 * 注意：
 * a) 注意Value一定不能重复，跟key无关；key重复就是覆盖原来的值
 * b) 如果inverse.get获取不到值就统一返回null
 * 
 * @author Tony Zhao
 * @version $Id: BiMapUtilize.java, v 0.1 2022-05-21 10:32 PM Tony Zhao Exp $$
 */
public class BiMapUtilize {

    public static void main(String[] args) {
        // 基础操作：反向通过Value获取Key
        reverseGetKey();

        // 普通hashMap被包装成BiMap
        mapEncapsulation();

        // 枚举作为key值的BiMap
        enumKeyBiMap();

        // 不可变的BiMap
        immutableBiMap();
    }

    public static void reverseGetKey() {
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        // 如果Value值重复，在put放入的时候就会报错
        // key重复就是改写原来的值
        // 如果inverse.get获取不到值就统一返回null
        empIDNameMap.put(101, "Mahesh");
        empIDNameMap.put(102, "Sohan");
        empIDNameMap.put(103, "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));
    }

    public static void mapEncapsulation() {
        Map<String, String> reflection = new HashMap<>();

        reflection.put("system_error", "系统错误");
        reflection.put("remote_call_fail", "远程方法调用错误");
        reflection.put("db_access_error", "数据库访问出错");

        // 如果Value值重复，在create的时候就一定会报错
        BiMap<String, String> biReflection = HashBiMap.create(reflection);

        System.out.println(biReflection.inverse().get("系统错误"));
    }

    public static void enumKeyBiMap() {
        // 如果用枚举作为key键，则要用`EnumHashBiMap`这个类，`HashBiMap`并没有提供Class类型的构造函数
        BiMap<OperatorType, String> stringBiMap = EnumHashBiMap.create(OperatorType.class);
        stringBiMap.put(OperatorType.ADVERTISER, "金主爸爸");
        stringBiMap.put(OperatorType.UP, "创作者");

        System.out.println(stringBiMap.inverse().get("金主爸爸"));
    }

    public static void immutableBiMap() {
        // 创建不可变的BiMap，一旦put或remove肯定会报错
        // 空格的value也是可以正确映射到Key值的
        BiMap<String, String> countries = new ImmutableBiMap.Builder<String, String>()
            .put("Shanghai", "China").put("Los Angeles", "United State of America").build();

        System.out.println(countries.inverse().get("United State of America"));
    }

}