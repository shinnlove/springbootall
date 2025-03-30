/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tony Zhao
 * @version $Id: MaterialTypeEnums.java, v 0.1 2025-03-07 16:10 Tony Zhao Exp $$
 */
@Getter
@AllArgsConstructor
public enum MaterialTypeEnums {

    NO_LIMITATION(-1, "无限制"),

    /**
     * 普通
     */
    NORMAL(0, "普通"),
    /**
     * 金闪闪
     */
    MATERIAL_TYPE_UP(1, "材质升级"),
    ;

    public final int code;

    public final String desc;

    /** 所有种类筛选 */
    public static final List<String> ALL_MATERIAL_TYPE_NAMES = Arrays.stream(MaterialTypeEnums.values()).map(MaterialTypeEnums::name).collect(Collectors.toList());

    public static MaterialTypeEnums getByName(String name) {
        try {
            MaterialTypeEnums typeEnums = MaterialTypeEnums.valueOf(name);
            return typeEnums;
        } catch (IllegalArgumentException e) {
            // unlimited
            return null;
        }
    }

    public static String getDescByCode(int code) {
        for (MaterialTypeEnums value : MaterialTypeEnums.values()) {
            if (value.code == code) {
                return value.desc;
            }
        }
        return NORMAL.desc;
    }

    public static String getNameByCode(int code) {
        for (MaterialTypeEnums value : MaterialTypeEnums.values()) {
            if (value.code == code) {
                return value.name();
            }
        }
        return NORMAL.name();
    }

}