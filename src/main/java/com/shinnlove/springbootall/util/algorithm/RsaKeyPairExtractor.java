/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.algorithm;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tony Zhao
 * @version $Id: RsaKeyPairExtractor.java, v 0.1 2025-05-22 14:36 Tony Zhao Exp $$
 */
public class RsaKeyPairExtractor {

    /**
     * 从 PKCS#8 PEM 文件中读取私钥并推导出对应公钥
     *
     * @param pemFilePath
     * @return
     * @throws Exception
     */
    public static KeyPair loadKeyPairFromPrivatePem(String pemFilePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // 二进制读取后转成明文
        byte[] fileContent = Files.readAllBytes(Paths.get(pemFilePath));
        String pemFileContent = new String(fileContent, RSAEncryptConstant.CHARSET_ENCODE);

        // 自动识别类型并提取base64加密Content
        String base64Content = extractPemBase64BlockFromFileContent(pemFileContent);

        // 提取出原生的RSA私钥二进制
        byte[] der = Base64.getDecoder().decode(base64Content);

        return loadKeyPairFromDerBytes(der);
    }

    /**
     * 给定config文件中的私钥内容（不含begin或end的头尾部），实例化成PrivateKey，并推导计算生成PublicKey。
     *
     * @param configBase64KeyContent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static KeyPair loadKeyPairFromConfigBase64Content(String configBase64KeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // 将配置base64编码的key内容转成der字节数组
        byte[] der = Base64.getDecoder().decode(configBase64KeyContent);

        return loadKeyPairFromDerBytes(der);
    }

    /**
     * 使用完整私钥der的字节数组计算生成公私钥对。
     *
     * @param der
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static KeyPair loadKeyPairFromDerBytes(byte[] der) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 1. 构造 PKCS#8 私钥对象
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(der);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAEncryptConstant.RSA_ALGORITHM_NAME);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // 2. 尝试提取模数 n 和公钥指数 e，推导出公钥
        if (privateKey instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey crtKey = (RSAPrivateCrtKey) privateKey;
            BigInteger modulus = crtKey.getModulus();       // n
            BigInteger publicExponent = crtKey.getPublicExponent(); // e

            RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
            PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

            return new KeyPair(publicKey, privateKey);
        } else {
            throw new IllegalArgumentException("无法从私钥中提取公钥参数（不是 RSAPrivateCrtKey 类型）");
        }
    }

    /**
     * 正则匹配 PEM 文件内容，提取出 base64 内容（自动处理换行和空格）。
     * 支持的 beginType 如：PRIVATE KEY、RSA PRIVATE KEY、PUBLIC KEY、ENCRYPTED PRIVATE KEY
     *
     * @param pemFileContent PEM 文件原始文本（含头尾）
     * @return 去除头尾和换行的 Base64 主体
     */
    public static String extractPemBase64BlockFromFileContent(String pemFileContent) {
        // 自动识别pem文件类型
        PemBlockType pemBeginType = PemBlockType.detectFromPemText(pemFileContent);
        String pemBeginTypeName = pemBeginType.getMessage();

        System.out.println("自动识别出的 PEM 类型为: " + pemBeginTypeName);

        // 支持带任意空格、换行的 PEM 内容
        // BEGIN 和 END 之间允许 0 个或多个空格
        String patternStr = "-----BEGIN[ ]*" + Pattern.quote(pemBeginTypeName) + "[ ]*-----([\\s\\S]*?)-----END[ ]*" + Pattern.quote(pemBeginTypeName) + "[ ]*-----";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(pemFileContent);

        if (matcher.find()) {
            String base64Block = matcher.group(1);
            return base64Block.replaceAll("\\s", "");  // 清除换行、空格、tab
        } else {
            throw new IllegalArgumentException("无效 PEM 格式或找不到 BEGIN/END " + pemBeginTypeName);
        }
    }

}
