/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.models;

/**
 * @author Tony Zhao
 * @version $Id: TaskParams.java, v 0.1 2024-05-08 16:25 Tony Zhao Exp $$
 */
public class TaskParams {

    private int priority = 0;

    private int reloadBalanceWeight = 10;

    public TaskParams() {
    }

    public TaskParams(int priority, int reloadBalanceWeight) {
        this.priority = priority;
        this.reloadBalanceWeight = reloadBalanceWeight;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getReloadBalanceWeight() {
        return reloadBalanceWeight;
    }

    public void setReloadBalanceWeight(int reloadBalanceWeight) {
        this.reloadBalanceWeight = reloadBalanceWeight;
    }

    public String toString() {
        return "TaskParams(priority=" + this.getPriority() + ", reloadBalanceWeight=" + this.getReloadBalanceWeight() + ")";
    }

}