/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tony Zhao
 * @version $Id: RsaKeyPairExtractor.java, v 0.1 2025-05-22 14:36 Tony Zhao Exp $$
 */
public class RsaKeyPairExtractor {

    /**
     * 示例二：从给定文件路径中读取公私钥。
     *
     * 读取公钥，是因为要读取别人给你用来验签或加密内容的公钥，一般放在配置中心或文件中。
     *
     * @param privateKeyFilePath
     * @param publicKeyFilePath
     * @return
     * @throws Exception
     */
    public static KeyPair loadKeyPairBothFromPemFile(String privateKeyFilePath, String publicKeyFilePath) throws Exception {
        PrivateKey privateKey = loadPrivateKeyFromPemFile(privateKeyFilePath);
        PublicKey publicKey = loadPublicKeyFromPemFile(publicKeyFilePath);

        return new KeyPair(publicKey, privateKey);
    }

    /**
     * 示例二：根据文件路径 加载 PKCS#8 私钥
     *
     * @param pemFilePath
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKeyFromPemFile(String pemFilePath) throws Exception {

        // 文件中提取秘钥二进制
        byte[] der = readKeyDerBytesFromFile(pemFilePath);

        // 生成pkcs#8格式的私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(der);

        // 读取私钥
        return KeyFactory.getInstance(RSAEncryptConstant.RSA_ALGORITHM_NAME).generatePrivate(keySpec);
    }

    /**
     * 示例二：根据文件路径 加载 X.509 公钥。
     *
     * 最佳实践：
     * 1) 如果是别人给的公钥，用来验证别人签名、或者加密数据给别人，可以用这个方法读取；
     * 2) 如果是自己的公钥，不建议直接读取，建议从完整私钥推导。
     *
     * 使用如下命令:
     *
     * ```shell
     *
     * # 从私钥文件导出公钥
     * openssl rsa -in rsa_pkcs1.pem -pubout -out public_key.pem
     *
     * # 从pkcs#8格式的私钥导出公钥
     * openssl rsa -in rsa_pkcs8.pem -pubout -out public_key.pem
     *
     * ```
     *
     * @param pemFilePath
     * @return
     * @throws Exception
     */
    public static PublicKey loadPublicKeyFromPemFile(String pemFilePath) throws Exception {

        // 文件中提取秘钥二进制
        byte[] der = readKeyDerBytesFromFile(pemFilePath);

        // 标准X.509的公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(der);

        // 读取公钥
        return KeyFactory.getInstance(RSAEncryptConstant.RSA_ALGORITHM_NAME).generatePublic(keySpec);
    }

    /**
     * 公共方法：从文件中读取无论是公钥还是私钥的der二进制内容。
     *
     * @param pemFilePath
     * @return
     * @throws Exception
     */
    private static byte[] readKeyDerBytesFromFile(String pemFilePath) throws Exception {

        if (StringUtils.isBlank(pemFilePath)) {
            throw new IllegalArgumentException("pem file path empty, cannot extract private or public key from file.");
        }

        // 二进制读取后转成明文
        byte[] fileContent = Files.readAllBytes(Paths.get(pemFilePath));
        String pemFileContent = new String(fileContent, RSAEncryptConstant.CHARSET_ENCODE);

        // 明文中提取base64的Key
        String base64KeyContent = RsaKeyPairExtractor.extractPemBase64BlockFromFileContent(pemFileContent);

        // 转成解码后的der字节数组
        return Base64.getDecoder().decode(base64KeyContent);
    }

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

    /**
     * 示例三：给定config文件中的私钥内容（不含begin或end的头尾部），实例化成PrivateKey。
     *
     * @param configBase64KeyContent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey loadPrivateKeyFromConfigBase64KeyContent(String configBase64KeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 将配置base64编码的key内容转成der字节数组
        byte[] der = Base64.getDecoder().decode(configBase64KeyContent);

        // 生成pkcs#8格式的私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(der);

        // 读取私钥
        return KeyFactory.getInstance(RSAEncryptConstant.RSA_ALGORITHM_NAME).generatePrivate(keySpec);
    }

}
