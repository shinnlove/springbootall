/**
 * Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.shinnlove.springbootall.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 库存变化类型枚举。
 */
@Getter
@AllArgsConstructor
public enum StorageChangeTypeEnum {

    UNKNOWN(0, "库存未变更"),

    STORAGE_LOCKED( 1, "库存已锁定"),

    STORAGE_SOLD(2, "库存已扣减"),

    STORAGE_RETURNED(3, "库存超时已退还"),

    ;

    private final int       code;
    private final String    desc;

    /**
     * 根据 value 转换成当前枚举
     *
     * @param code value
     * @return 枚举
     */
    public static StorageChangeTypeEnum getByCode(int code) {
        for (StorageChangeTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return null;
    }

    public static String getDescByValue(int code) {
        for (StorageChangeTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }

        return "";
    }

}
