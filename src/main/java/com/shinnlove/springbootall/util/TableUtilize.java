/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Guava的table用法，类似于二维数组创建的一个多值Map。
 * 
 * 优点：
 * a) 可以按行操作数据(基础)、可以按列选择数据，
 * b) 不用在使用HashMap的时候get(key)还要判断是否null新创建。
 * 
 * @author Tony Zhao
 * @version $Id: TableUtilize.java, v 0.1 2022-05-22 3:03 PM Tony Zhao Exp $$
 */
public class TableUtilize {

    public static void main(String[] args) {
        //Table<R,C,V> == Map<R,Map<C,V>>
        /*
         *  Company: IBM, Microsoft, TCS
         *  IBM 		-> {101:Mahesh, 102:Ramesh, 103:Suresh}
         *  Microsoft 	-> {101:Sohan, 102:Mohan, 103:Rohan }
         *  TCS 		-> {101:Ram, 102: Shyam, 103: Sunil }
         *
         * */

        // 创建一个HashBase的双key表，只能三个元素
        Table<String, String, String> employeeTable = HashBasedTable.create();

        //initialize the table with employee details
        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");

        employeeTable.put("Microsoft", "111", "Sohan");
        employeeTable.put("Microsoft", "112", "Mohan");
        employeeTable.put("Microsoft", "113", "Rohan");
        employeeTable.put("Microsoft", "102", "Tony");

        employeeTable.put("TCS", "121", "Ram");
        employeeTable.put("TCS", "122", "Shyam");
        employeeTable.put("TCS", "123", "Sunil");

        //get Map corresponding to IBM
        System.out.println("List of IBM Employees");
        Map<String, String> ibmEmployees = employeeTable.row("IBM");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        // 可以直接单独判断Row和Column是否存在
        System.out.println(employeeTable.containsRow("TCS"));
        System.out.println(employeeTable.containsColumn("110"));
        System.out.println(employeeTable.containsValue("evelyn"));

        //get all the unique keys of the table
        // 获取所有行号(Key值)
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for (String employer : employers) {
            System.out.print(employer + " ");
        }
        System.out.println();

        // 直接获取表格所有列
        Set<String> ids = employeeTable.columnKeySet();
        System.out.print("Ids: ");
        for (String id : ids) {
            System.out.print(id + " ");
        }
        System.out.println();

        // 直接根据列条件锁定多行，哪怕是不一样的row!
        Map<String, String> EmployerMap = employeeTable.column("102");
        for (Map.Entry<String, String> entry : EmployerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }

    }

}