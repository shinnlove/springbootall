/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shinnlove.springbootall.util.LongJsonSerializer;

/**
 * Student domain model.
 * 
 * @author Tony Zhao
 * @version $Id: Student.java, v 0.1 2022-03-14 10:03 AM Tony Zhao Exp $$
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -7942604549849788112L;

    @JsonSerialize(using = LongJsonSerializer.class)
    private Long              studentId;
    private String            studentName;
    private int               sex;

    public Student() {
    }

    public Student(Long studentId, String studentName, int sex) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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