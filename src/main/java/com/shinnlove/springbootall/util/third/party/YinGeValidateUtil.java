/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.SignatureException;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.constants.MonthTicketBizConfig;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 印鸽校验接口。
 *
 * @author Tony Zhao
 * @version $Id: YinGeValidateUtil.java, v 0.1 2025-03-04 17:27 Tony Zhao Exp $$
 */
public class YinGeValidateUtil {

    /** 三方接口需要校验的公共字段 */
    public static final Map<String, Class<?>> SIGNATURE_REQUIRED_FIELDS_MAP;

    static {
        SIGNATURE_REQUIRED_FIELDS_MAP = new HashMap<String, Class<?>>() {
            private static final long serialVersionUID = 2491304970374686971L;
            {
                put(Biz3rdPartyConstant.TIMESTAMP, Integer.class);
                put(Biz3rdPartyConstant.RESELLER_FLAG, String.class);
                put(Biz3rdPartyConstant.VERSION, Integer.class);
                put(Biz3rdPartyConstant.SIGN_TYPE, Integer.class);
                put(Biz3rdPartyConstant.SIGN, String.class);
            }
        };
    }

    /**
     * 通用签名参数校验。
     *
     * @param formData 表单数据
     * @return YinGeSignature 对象
     * @throws IllegalArgumentException 如果校验失败
     */
    public static YinGeSignature validateSignatureRequiredFields(MultiValueMap<String, Object> formData) throws IllegalArgumentException {
        if (MapUtils.isEmpty(formData)) {
            throw new IllegalArgumentException("Input parameters cannot be null");
        }

        // 校验所有必填字段
        for (Map.Entry<String, Class<?>> entry : SIGNATURE_REQUIRED_FIELDS_MAP.entrySet()) {
            String fieldName = entry.getKey();
            Class<?> fieldType = entry.getValue();

            validateField(formData, fieldName, fieldType);
        }

        // 特殊校验：SIGN_TYPE 必须为 2
        int signType = validateAndExtract(formData, Biz3rdPartyConstant.SIGN_TYPE, Integer.class);
        if (2 != signType) {
            throw new IllegalArgumentException("Field " + Biz3rdPartyConstant.SIGN_TYPE + " must be version 2.");
        }

        int timestamp = validateAndExtract(formData, Biz3rdPartyConstant.TIMESTAMP, Integer.class);
        String resellerFlag = validateAndExtract(formData, Biz3rdPartyConstant.RESELLER_FLAG, String.class);
        int version = validateAndExtract(formData, Biz3rdPartyConstant.VERSION, Integer.class);
        String sign = validateAndExtract(formData, Biz3rdPartyConstant.SIGN, String.class);

        return YinGeSignature.builder()
                .timestamp(timestamp)
                .resellerFlag(resellerFlag)
                .version(version)
                .signType(signType)
                .sign(sign)
                .build();
    }

    /**
     * 校验单个字段是否存在且类型正确。
     *
     * @param formData  表单数据
     * @param fieldName 字段名
     * @param fieldType 字段类型
     * @throws IllegalArgumentException 如果校验失败
     */
    private static void validateField(MultiValueMap<String, Object> formData, String fieldName, Class<?> fieldType) throws IllegalArgumentException {
        if (!formData.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " cannot be null");
        }

        String fieldValue = formData.getFirst(fieldName).toString();
        if (StringUtils.isBlank(fieldValue)) {
            throw new IllegalArgumentException("Field " + fieldName + " cannot be empty");
        }

        if (fieldType == Integer.class) {
            try {
                Integer.parseInt(fieldValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Field " + fieldName + " must be a number");
            }
        }
    }

    /**
     * 校验印鸽签名是否正确，不正确则拒绝。
     *
     * @param signature
     * @param theirSign
     * @param <T>
     * @throws SignatureException
     * @throws IllegalArgumentException
     */
    public static <T> void validateSignature(YinGeSignature signature, String theirSign) throws SignatureException, IllegalArgumentException {
        // 字典序升序
        Map<String, Object> inputParams = new TreeMap<>();

        // 提取必要签名字段
        inputParams.put(Biz3rdPartyConstant.TIMESTAMP, signature.getTimestamp());
        inputParams.put(Biz3rdPartyConstant.RESELLER_FLAG, signature.getResellerFlag());
        inputParams.put(Biz3rdPartyConstant.VERSION, signature.getVersion());
        inputParams.put(Biz3rdPartyConstant.SIGN_TYPE, signature.getSignType());

        // 增加secret
        MonthTicketBizConfig config = new MonthTicketBizConfig();
        inputParams.put(Biz3rdPartyConstant.SECRET, config.getSecret());

        // 计算出来的sign
        String calculateSign = SignatureUtil.getSignature(inputParams);

        if (!calculateSign.equalsIgnoreCase(theirSign)) {
            throw new SignatureException(BusinessCode.SIGNATURE_VALIDATE_FAILED, "signature is not correct");
        }
    }

    /**
     * 通用字段提取。
     *
     * @param formData 表单数据
     * @param fieldName 字段名称
     * @param clazz 目标类型
     * @return 提取并转换后的字段值
     * @throws IllegalArgumentException 如果字段不存在或转换失败
     */
    public static <T> T validateAndExtract(MultiValueMap<String, Object> formData, String fieldName, Class<T> clazz) throws IllegalArgumentException {
        // 校验输入参数
        if (MapUtils.isEmpty(formData)) {
            throw new IllegalArgumentException("Input parameters cannot be null");
        }

        // 校验字段是否存在
        if (!formData.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " cannot be null");
        }

        // 获取字段值
        Object objValue = formData.getFirst(fieldName);
        if (Objects.isNull(objValue)) {
            throw new IllegalArgumentException("Field " + fieldName + " cannot be null");
        }

        // 根据目标类型进行转换
        try {
            if (clazz == String.class) {
                return clazz.cast(objValue.toString()); // 转换为字符串
            } else if (clazz == Integer.class || clazz == int.class) {
                return clazz.cast(Integer.valueOf(objValue.toString())); // 转换为整数
            } else if (clazz == Long.class || clazz == long.class) {
                return clazz.cast(Long.valueOf(objValue.toString())); // 转换为长整数
            } else if (clazz == Double.class || clazz == double.class) {
                return clazz.cast(Double.valueOf(objValue.toString())); // 转换为双精度浮点数
            } else if (clazz == Boolean.class || clazz == boolean.class) {
                return clazz.cast(Boolean.valueOf(objValue.toString())); // 转换为布尔值
            } else {
                // 如果是自定义对象类型，使用 Fastjson 进行反序列化
                return JSON.parseObject(objValue.toString(), clazz);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Field " + fieldName + " must be a number", e);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Field " + fieldName + " cannot be parsed to " + clazz.getName(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert field " + fieldName + " to type " + clazz.getName(), e);
        }
    }



}
