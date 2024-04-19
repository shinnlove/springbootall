/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Tony Zhao
 * @version $Id: PageResult.java, v 0.1 2024-04-19 19:59 Tony Zhao Exp $$
 */
public class PageResult <T> implements Serializable {

    private long total;

    private List<T> data;

    public PageResult() {
    }

    public PageResult(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public final static PageResult EMPTY_PAGE_RESULT = new PageResult(0L, Collections.emptyList());

}