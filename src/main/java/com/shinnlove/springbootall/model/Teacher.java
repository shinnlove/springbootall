/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Teacher domain model.
 * 
 * @author Tony Zhao
 * @version $Id: Teacher.java, v 0.1 2022-03-14 10:09 AM Tony Zhao Exp $$
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = -6199241165631890909L;

    private String            teacherId;
    private String            teacherName;
    private int               sex;

    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName, int sex) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.sex = sex;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}