/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.algorithm;

import java.util.regex.Pattern;

/**
 * 支持多种 PEM 块类型的枚举类。
 * 每个类型包含一个 code 和 message，其中 message 用于匹配 PEM 文件头尾。
 *
 * @author Tony Zhao
 * @version $Id: PemBlockType.java, v 0.1 2025-05-22 14:10 Tony Zhao Exp $$
 */
public enum PemBlockType {

    PRIVATE_KEY(1, "PRIVATE KEY"),

    RSA_PRIVATE_KEY(2, "RSA PRIVATE KEY"),

    ENCRYPTED_PRIVATE_KEY(3, "ENCRYPTED PRIVATE KEY"),

    PUBLIC_KEY(4, "PUBLIC KEY");

    private final int code;

    private final String message;

    PemBlockType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 判断某个文本是否包含该类型的 PEM 块标识
     */
    public static PemBlockType detectFromPemText(String pemText) {
        for (PemBlockType type : values()) {
            String pattern = "-----BEGIN[ ]*" + Pattern.quote(type.getMessage()) + "[ ]*-----";
            if (Pattern.compile(pattern).matcher(pemText).find()) {
                return type;
            }
        }
        throw new IllegalArgumentException("未识别的 PEM 类型：无法从文本自动判断 PEM 类型");
    }

}
