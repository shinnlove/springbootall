/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.process.consts;

/**
 * Status machine metadata parse constant symbol.
 * 
 * @author Tony Zhao
 * @version $Id: MetadataConstant.java, v 0.1 2022-01-29 2:56 PM Tony Zhao Exp $$
 */
public class MetadataConstant {

    /** define frequent used symbol */
    public static final String COMMA     = ",";
    public static final String UNDERLINE = "_";

    /** search base type */

    public static final String BASE_SUPER_CLASS    = "java.lang.Object";

    /** basic type */

    public static final String SMALL_BOOLEAN_CLASS = "boolean";
    public static final String SMALL_INT_CLASS     = "int";
    public static final String SMALL_LONG_CLASS    = "long";
    public static final String SMALL_FLOAT_CLASS   = "float";
    public static final String SMALL_DOUBLE_CLASS  = "double";

    /** wrapper type */

    public static final String BOOLEAN_CLASS       = "java.lang.Boolean";
    public static final String STRING_CLASS        = "java.lang.String";

    public static final String INTEGER_CLASS       = "java.lang.Integer";
    public static final String LONG_CLASS          = "java.lang.Long";

    public static final String FLOAT_CLASS         = "java.lang.Float";
    public static final String DOUBLE_CLASS        = "java.lang.Double";

    /** collection type */

    public static final String COLLECTION_PREFIX   = "java.util";
    public static final String LIST_CLASS          = "java.util.List";
    public static final String SET_CLASS           = "java.util.Set";
    public static final String MAP_CLASS           = "java.util.Map";

}