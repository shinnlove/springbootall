/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.wrap;

/**
 * @author Tony Zhao
 * @version $Id: ReflectWrapWithResult.java, v 0.1 2022-02-12 10:24 PM Tony Zhao Exp $$
 */
@FunctionalInterface
public interface ReflectWrapWithResult {

    Object call() throws Exception;

}