/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Guava loading cache.
 * 
 * LoadingCache<K,V>的K是检索的值，一般是主键或外键，V是需要缓存在内存中或者回源去数据库找的值。
 * 
 * @author Tony Zhao
 * @version $Id: LoadingCacheUtilize.java, v 0.1 2022-05-22 4:48 PM Tony Zhao Exp $$
 */
public class LoadingCacheUtilize {

    private LoadingCache<String, Employee> employeeCache = CacheBuilder.newBuilder()
        .maximumSize(100) // maximum 100 records can be cached
        .expireAfterAccess(30, TimeUnit.MINUTES) // cache will expire after 30 minutes of access
        .build(new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String key) throws Exception {
                //make the expensive call
                return getFromDatabase(key);
            }
        });

    public static void main(String[] args) {
        LoadingCacheUtilize utilize = new LoadingCacheUtilize();
        // 使用guava cache，只有第一次是走回源的
        utilize.loadCache();
    }

    public void loadCache() {

        try {
            //on first invocation, cache will be populated with corresponding
            //employee record
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

            //second invocation, data will be returned from cache
            System.out.println("Invocation #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private Employee getFromDatabase(String empId) {
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String, Employee> database = new HashMap<>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for " + empId);
        return database.get(empId);
    }

    public static class Employee implements Serializable {
        private static final long serialVersionUID = -54607603270077887L;

        private String            name;
        private String            country;
        private String            empId;

        public Employee(String name, String country, String empId) {
            this.name = name;
            this.country = country;
            this.empId = empId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }

    }

}