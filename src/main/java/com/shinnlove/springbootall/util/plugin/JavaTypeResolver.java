/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.plugin;

import java.sql.Timestamp;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * @author Tony Zhao
 * @version $Id: JavaTypeResolverForMission.java, v 0.1 2022-01-11 12:37 AM Tony Zhao Exp $$
 */
public class JavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public JavaTypeResolver() {
        super();
        super.typeMap.put(-6, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT",
            new FullyQualifiedJavaType(Integer.class.getName())));
        super.typeMap.put(93, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TIMESTAMP",
            new FullyQualifiedJavaType(Timestamp.class.getName())));
    }

}