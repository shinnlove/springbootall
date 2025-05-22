/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Tony Zhao
 * @version $Id: LegacySignatureValidateUtil.java, v 0.1 2025-03-06 23:53 Tony Zhao Exp $$
 */
public class LegacySignatureValidateUtil {

    /** 三方接口需要校验的公共字段 */
    public static final List<String> signatureFields;

    static {
        signatureFields = Arrays.asList("timestamp", "sign", "resellerFlag", "version", "signType");
    }

    /**
     * 校验印鸽签名是否正确，不正确则拒绝。
     *
     * @param inputParams
     * @param <T>
     * @throws IllegalArgumentException
     */
    @Deprecated
    public static <T> void validateSignatureOld(T inputParams) throws IllegalArgumentException {
        // 提取请求对象签名信息
        YinGeSignature yinGeSignature = validateAndExtractSignature(inputParams);

        // 再校验印鸽签名
        Map<String, Object> signMap = new HashMap<>();
        signMap.put(Biz3rdPartyConstant.TIMESTAMP, yinGeSignature.getTimestamp());
        signMap.put(Biz3rdPartyConstant.RESELLER_FLAG, yinGeSignature.getResellerFlag());
        signMap.put(Biz3rdPartyConstant.VERSION, yinGeSignature.getVersion());
        signMap.put(Biz3rdPartyConstant.SIGN_TYPE, yinGeSignature.getSignType());

        // 自己计算一把
        String calculateStr = SignatureUtil.getSignature(signMap);

        // 不匹配报错
        if (!calculateStr.equalsIgnoreCase(yinGeSignature.getSign())) {
            throw new IllegalArgumentException("signature is not correct");
        }
    }

    /**
     * 校验签名字段是否存在，并返回一个 YinGeSignature 对象，而后校验印鸽签名是否正确，不正确则拒绝。
     *
     * @param inputParams 输入参数对象
     * @param <T>         泛型类型
     * @return YinGeSignature 对象
     * @throws IllegalArgumentException 如果字段不存在或校验失败
     */
    @Deprecated
    private static <T> YinGeSignature validateAndExtractSignature(T inputParams) throws IllegalArgumentException {

        if (Objects.isNull(inputParams)) {
            throw new IllegalArgumentException("Input parameters cannot be null");
        }

        // 校验签名字段是否存在
        validateSignatureFieldsExist(inputParams);

        // 提取并返回 YinGeSignature 对象
        return extractYinGeSignature(inputParams);
    }

    /**
     * 校验签名字段是否存在
     *
     * @param inputParams 输入参数对象
     * @param <T>         泛型类型
     * @throws IllegalArgumentException 如果字段不存在
     */
    @Deprecated
    private static <T> void validateSignatureFieldsExist(T inputParams) throws IllegalArgumentException {
        Class<?> clazz = inputParams.getClass();

        // 遍历类的继承层次结构，直到找到 YinGeSignature 类
        while (clazz != null && clazz != Object.class) {
            if (clazz == YinGeSignature.class) {
                // 检查所有必填字段是否存在
                for (String fieldName : signatureFields) {
                    try {
                        Field field = clazz.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        if (field.get(inputParams) == null) {
                            throw new IllegalArgumentException("Field " + fieldName + " cannot be null");
                        }
                    } catch (NoSuchFieldException e) {
                        throw new IllegalArgumentException("Field " + fieldName + " does not exist in " + clazz.getSimpleName(), e);
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException("Failed to access field: " + fieldName, e);
                    }
                }
                break;
            }
            clazz = clazz.getSuperclass();
        }

        if (clazz == null) {
            throw new IllegalArgumentException("Input parameters must be a subclass of YinGeSignature");
        }
    }

    /**
     * 提取 YinGeSignature 对象
     *
     * @param inputParams 输入参数对象
     * @param <T>         泛型类型
     * @return YinGeSignature 对象
     * @throws IllegalArgumentException 如果提取失败
     */
    @Deprecated
    private static <T> YinGeSignature extractYinGeSignature(T inputParams) throws IllegalArgumentException {
        try {
            YinGeSignature signature = new YinGeSignature();

            Class<?> clazz = inputParams.getClass();

            // 遍历类的继承层次结构，直到找到 YinGeSignature 类
            while (clazz != null && clazz != Object.class) {
                if (clazz == YinGeSignature.class) {
                    // 获取 YinGeSignature 类的所有字段
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        // 将字段值复制到新的 YinGeSignature 对象中
                        field.set(signature, field.get(inputParams));
                    }
                    break;
                }
                clazz = clazz.getSuperclass();
            }

            if (clazz == null) {
                throw new IllegalArgumentException("Input parameters must be a subclass of YinGeSignature");
            }

            return signature;

        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to extract YinGeSignature fields", e);
        }
    }

}