/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

public class SignatureUtil {

    public static void main(String[] args) throws Exception {
        // 原始JSON数据
        String json = "{\"customizeNo\":\"EGBVCXZ\",\"expressTrace\":[{\"time\":\"2022-04-10 18:41:19\",\"context\":\"重庆市大渡口区公司 已发出,下一站 重庆转运中心\"},{\"time\":\"2022-04-10 18:41:09\",\"context\":\"重庆市大渡口区公司 已揽收, 陈艳玲(15223428587)\"}],\"outTradeNo\":\"OID9876543210XXXX\",\"resellerFlag\":\"yingeRich\",\"secret\":\"sxxxxxxxxt\",\"signType\":2,\"timestamp\":1662633066,\"version\":1}";
        String json2 = "{\"customizeNo\":\"EGBVCXZ\",\"expressTrace\":[{\"time\":\"2022-04-10 18:41:19\",\"context\":\"重庆市大渡口区公司 已发出,下一站 重庆转运中心\"},{\"time\":\"2022-04-10 18:41:09\",\"context\":\"重庆市大渡口区公司 已揽收, 陈艳玲(15223428587)\"},{\"time\":\"2024-04-10 18:41:09\",\"context\":\"上海市浦东新区 已揽收, 陈艳玲(15223428589)\"}],\"outTradeNo\":\"OID9876543210XXXX\",\"resellerFlag\":\"yingeRich\",\"customizedNo\":\"124678954\",\"diyField\":\"这是一个自定义字段\",\"secret\":\"sxxxxxxxxt\",\"signType\":2,\"timestamp\":1662633066,\"version\":1}";

        String compareStr = "customizeNo=EGBVCXZ&expressTrace=[{\"time\":\"2022-04-10 18:41:19\",\"context\":\"重庆市大渡口区公司 已发出,下一站 重庆转运中心\"},{\"time\":\"2022-04-10 18:41:09\",\"context\":\"重庆市大渡口区公司 已揽收, 陈艳玲(15223428587)\"}]&outTradeNo=OID9876543210XXXX&resellerFlag=yingeRich&secret=sxxxxxxxxt&signType=2&timestamp=1662633066&version=1";
        String compareStr2 = "customizeNo=EGBVCXZ&customizedNo=124678954&diyField=这是一个自定义字段&expressTrace=[{\"time\":\"2022-04-10 18:41:19\",\"context\":\"重庆市大渡口区公司 已发出,下一站 重庆转运中心\"},{\"time\":\"2022-04-10 18:41:09\",\"context\":\"重庆市大渡口区公司 已揽收, 陈艳玲(15223428587)\"},{\"time\":\"2024-04-10 18:41:09\",\"context\":\"上海市浦东新区 已揽收, 陈艳玲(15223428589)\"}]&outTradeNo=OID9876543210XXXX&resellerFlag=yingeRich&secret=sxxxxxxxxt&signType=2&timestamp=1662633066&version=1";

        // 将JSON字符串转换为Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.readValue(json2, Map.class);

        // 生成签名
        String signature = generateSignature(dataMap, compareStr2);
        System.out.println("Generated Signature: " + signature);
    }

    public static String generateSignature(Map<String, Object> dataMap, String compareStr) throws Exception {
        // 准备待签名的参数列表
        Map<String, Object> sortedMap = new TreeMap<>(dataMap);

        // 将expressTrace字段转换为JSON字符串
        if (sortedMap.containsKey("expressTrace")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
            String expressTraceJson = objectMapper.writeValueAsString(sortedMap.get("expressTrace"));
            sortedMap.put("expressTrace", expressTraceJson);
        }

        // 拼接参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            sb.append(key).append("=").append(value).append("&");
        }

        // 去掉最后一个"&"
        String paramString = sb.toString();
        if (paramString.endsWith("&")) {
            paramString = paramString.substring(0, paramString.length() - 1);
        }

        // 打印待签名字符串（用于调试）
        System.out.println("待签名字符串: " + paramString);
        System.out.println("是否字符串相等:" + paramString.equals(compareStr));

        // 进行MD5加密
        String signature = md5(paramString).toLowerCase();

        return signature;
    }

    /**
     * 使用Java自带的MessageDigest实现MD5加密
     */
    public static String md5(String input) {
        try {
            // 创建MessageDigest实例，指定MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将输入字符串转换为字节数组并进行哈希计算
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }
}