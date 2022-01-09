/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.model;

import java.io.Serializable;

/**
 * Domain model mapping to the document in elasticsearch index.
 * 
 * @author Tony Zhao
 * @version $Id: User.java, v 0.1 2022-01-09 8:16 PM Tony Zhao Exp $$
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2196809939294679188L;

    private String            name;
    private String            sex;
    private int               age;

    public User() {
    }

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}