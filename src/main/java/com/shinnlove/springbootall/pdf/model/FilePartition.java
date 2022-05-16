/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.pdf.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Tony Zhao
 * @version $Id: FilePartition.java, v 0.1 2022-05-15 5:06 PM Tony Zhao Exp $$
 */
public class FilePartition implements Serializable, Comparable<FilePartition> {

    private static final long serialVersionUID = -2797899300067386795L;

    private int               lastChapter;
    private String            fileName;

    public FilePartition() {
    }

    public FilePartition(String fileName) {
        this.fileName = fileName;
    }

    public FilePartition(int lastChapter, String fileName) {
        this.lastChapter = lastChapter;
        this.fileName = fileName;
    }

    public int getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(int lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int compareTo(FilePartition o) {
        if (this.getLastChapter() < o.getLastChapter()) {
            // small then self is ahead
            return -1;
        } else if (this.getLastChapter() == o.getLastChapter()) {
            return 0;
        } else {
            // self is behind
            return 1;
        }
    }

}