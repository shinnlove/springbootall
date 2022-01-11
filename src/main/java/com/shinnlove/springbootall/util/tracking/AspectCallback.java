/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.tracking;

/**
 * @author Tony Zhao
 * @version $Id: AspectCallback.java, v 0.1 2021-08-24 4:52 PM Tony Zhao Exp $$
 */
@FunctionalInterface
public interface AspectCallback {

    Object call() throws Throwable;

}
