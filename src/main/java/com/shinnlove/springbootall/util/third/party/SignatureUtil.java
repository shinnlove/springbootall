/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.third.party;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shinnlove.springbootall.exceptions.BusinessCode;
import com.shinnlove.springbootall.exceptions.SignatureException;
import com.shinnlove.springbootall.util.constants.Biz3rdPartyConstant;
import com.shinnlove.springbootall.util.constants.MonthTicketBizConfig;
import com.shinnlove.springbootall.util.third.party.dto.CustomizeInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeExpressInfo;
import com.shinnlove.springbootall.util.third.party.dto.YinGeResult;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 三方签名验签工具。
 *
 * @author Tony Zhao
 * @version $Id: SignatureUtil.java, v 0.1 2025-03-03 17:57 Tony Zhao Exp $$
 */
public class SignatureUtil {

    private static final Logger logger = LoggerFactory.getLogger(SignatureUtil.class);

    /**
     * 填充第三方公共签名参数。
     *
     * @param bizParamsMap
     * @throws SignatureException
     */
    public static void fillCommonSignature(Map<String, Object> bizParamsMap) throws SignatureException {

        if (Objects.isNull(bizParamsMap)) {
            bizParamsMap = new HashMap<>();
        }

        // 准备时间戳
        long timestampSeconds = System.currentTimeMillis() / 1000;
        // 1st. 填充时间戳
        bizParamsMap.put(Biz3rdPartyConstant.TIMESTAMP, timestampSeconds);

        // 准备公共参数
        MonthTicketBizConfig bizConfig = new MonthTicketBizConfig();

        // 2nd. ~ 4th. 填充公共字段
        bizParamsMap.put(Biz3rdPartyConstant.RESELLER_FLAG, bizConfig.getResellerFlag());
        bizParamsMap.put(Biz3rdPartyConstant.VERSION, bizConfig.getVersion());
        bizParamsMap.put(Biz3rdPartyConstant.SIGN_TYPE, bizConfig.getSignType());

        // 5th. 放入secret字段
        bizParamsMap.put(Biz3rdPartyConstant.SECRET, bizConfig.getSecret());

        // 生成
        String signature = getSignature(bizParamsMap);

        // 生成签名字段并填充
        bizParamsMap.put(Biz3rdPartyConstant.SIGN, signature);

        // at last, remove secret field
        bizParamsMap.remove(Biz3rdPartyConstant.SECRET);
    }

    /**
     * 生成签名、try...catch...异常处理
     *
     * @param allParamsMap
     * @return
     * @throws SignatureException
     */
    public static String getSignature(Map<String, Object> allParamsMap) throws SignatureException {

        if (MapUtils.isEmpty(allParamsMap)) {
            throw new SignatureException(BusinessCode.FAIL, "签名参数为空");
        }

        String signature = "";
        try {
            signature = generateSignature(allParamsMap);
        } catch (SignatureException e) {
            throw e;
        } catch (Exception e) {
            throw new SignatureException(BusinessCode.FAIL, e);
        }

        return signature;
    }

    static class NoEscapeSerializer implements ObjectSerializer {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            // 直接写入字符串，不进行转义
            serializer.out.writeString((String) object);
        }
    }

    /**
     * 字典序排序、拼接参数、MD5加密生成签名。
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    private static String generateSignature(Map<String, Object> dataMap) throws Exception {

        if (MapUtils.isEmpty(dataMap)) {
            return Biz3rdPartyConstant.EMPTY_STR;
        }

        // 准备待签名的参数列表
        final Map<String, Object> sortedMap = new TreeMap<>();

        // 先处理非expressTrace字段
        dataMap.forEach((k, v) -> {
            if (!Biz3rdPartyConstant.EXPRESS_TRACE.equalsIgnoreCase(k)) {
                sortedMap.put(k, v);
            }
        });

        // 将expressTrace字段转换为JSON字符串
        if (dataMap.containsKey(Biz3rdPartyConstant.EXPRESS_TRACE)) {
            Object expressTraceObj = dataMap.get(Biz3rdPartyConstant.EXPRESS_TRACE);
            // 禁用转义，生成不带反斜杠的 JSON 字符串
            String expressTraceJson = JSON.toJSONString(expressTraceObj, SerializerFeature.DisableCircularReferenceDetect);
            // 放入sortedMap中
            sortedMap.put(Biz3rdPartyConstant.EXPRESS_TRACE, expressTraceJson);
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

        // 进行MD5加密
        String afterSignature = md5(paramString).toLowerCase();

        // 打印待签名字符串和签名（用于调试）
        logger.warn("待签名字符串: {}.", paramString);
        logger.warn("计算出签名值: {}.", afterSignature);

        return afterSignature;
    }

    private static String generateSignature(Map<String, Object> dataMap, String compareStr) throws Exception {
        // 准备待签名的参数列表
        Map<String, Object> sortedMap = new TreeMap<>(dataMap);

        // 将expressTrace字段转换为JSON字符串
        if (sortedMap.containsKey(Biz3rdPartyConstant.EXPRESS_TRACE)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
            String expressTraceJson = objectMapper.writeValueAsString(sortedMap.get(Biz3rdPartyConstant.EXPRESS_TRACE));
            sortedMap.put(Biz3rdPartyConstant.EXPRESS_TRACE, expressTraceJson);
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
        System.out.println("待比对字符串: " + compareStr);
        System.out.println("两个字符串比对结果: " + compareStr.equalsIgnoreCase(paramString));

        // 进行MD5加密
        return md5(paramString).toLowerCase();
    }

    /**
     * 使用Java自带的MessageDigest实现MD5加密。
     *
     * @param input
     * @return
     * @throws SignatureException
     */
    private static String md5(String input) throws SignatureException {
        try {
            // 创建MessageDigest实例，指定MD5算法
            MessageDigest md = MessageDigest.getInstance(Biz3rdPartyConstant.SIGN_TYPE_MD5);
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
            throw new SignatureException(BusinessCode.ENCRYPT_MD5_FAILED, e);
        }
    }

    public static String mapToJsonString(Map<String, Object> map) {
        // 创建一个 TreeMap，保证键的字典序
        Map<String, Object> treeMap = new TreeMap<>(map);

        String json = "";
        // 使用 Jackson 将 Map 转换为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(treeMap);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public static void main(String[] args) throws Exception {
        // 原始JSON数据
        String json = "{\"customizeNo\":\"EGBVCXZ\",\"expressTrace\":[{\"time\":\"2022-04-10 18:41:19\",\"context\":\"重庆市大渡口区公司 已发出,下一站 重庆转运中心\"},{\"time\":\"2022-04-10 18:41:09\",\"context\":\"重庆市大渡口区公司 已揽收, 陈艳玲(15223428587)\"}],\"outTradeNo\":\"244326324342543266\",\"resellerFlag\":\"qddsipqddsipqddsip\",\"secret\":\"secret29472103dfe\",\"signType\":2,\"timestamp\":1741159164,\"version\":1}";

        String compareStr = "customizeNo=EGBVCXZ&expressTrace=[{\"time\":\"2022-04-10 18:41:19\",\"context\":\"重庆市大渡口区公司 已发出,下一站 重庆转运中心\"},{\"time\":\"2022-04-10 18:41:09\",\"context\":\"重庆市大渡口区公司 已揽收, 陈艳玲(15223428587)\"}]&outTradeNo=244326324342543266&resellerFlag=qddsipqddsipqddsip&secret=secret29472103dfe&signType=2&timestamp=1741159164&version=1";

        // 将JSON字符串转换为Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = objectMapper.readValue(json, Map.class);

        // 生成签名
        String signature = generateSignature(dataMap, compareStr);
        System.out.println("Generated Signature: " + signature);
    }

}