/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.algorithm;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Tony Zhao
 * @version $Id: EncodeSecretTextWithRSA.java, v 0.1 2025-05-21 17:32 Tony Zhao Exp $$
 */
public class EncodeSecretTextWithRSA {

    /**
     * 使用公钥加密明文文本内容成密文。
     *
     * @param originContent
     * @param publicKey
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encryptText(String originContent, PublicKey publicKey) throws UnsupportedEncodingException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] plaintextBytes = originContent.getBytes(RSAEncryptConstant.CHARSET_ENCODE);

        // 3. 公钥加密
        Cipher encryptCipher = Cipher.getInstance(RSAEncryptConstant.RSA_PADDING_MODE);
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = encryptCipher.doFinal(plaintextBytes);
        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Encrypted (Base64): " + encryptedBase64);

        return encryptedBase64;
    }

    /**
     * 使用私钥解密他人用公钥加密的密文文本内容。
     *
     * @param encryptedBase64
     * @param privateKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String decryptText(String encryptedBase64, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);

        // 4. 私钥解密
        Cipher decryptCipher = Cipher.getInstance(RSAEncryptConstant.RSA_PADDING_MODE);
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, RSAEncryptConstant.CHARSET_ENCODE);

        System.out.println("Decrypted: " + decryptedText);

        return decryptedText;
    }

    /**
     * 私钥签名
     *
     * @param signData
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String signWithPrivateKey(String signData, PrivateKey privateKey) throws Exception {

        Signature signature = Signature.getInstance(RSAEncryptConstant.SHA_256_RSA_ALGORITHM);
        signature.initSign(privateKey);

        signature.update(signData.getBytes(RSAEncryptConstant.CHARSET_ENCODE));
        byte[] signatureBytes = signature.sign();

        // 将签名字节数组转成Base64
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    /**
     * 公钥验签
     *
     * @param signData          需要签名的数据
     * @param base64Signature   Base64加密的签名
     * @param publicKey         可以验签的公钥
     * @return
     * @throws Exception
     */
    public static boolean verifySignWithPublicKey(String signData, String base64Signature, PublicKey publicKey) throws Exception {

        // 签名解码后转成字节数组
        byte[] signatureBytes = Base64.getDecoder().decode(base64Signature);

        Signature signature = Signature.getInstance(RSAEncryptConstant.SHA_256_RSA_ALGORITHM);
        signature.initVerify(publicKey);

        signature.update(signData.getBytes(RSAEncryptConstant.CHARSET_ENCODE));

        // 验签
        return signature.verify(signatureBytes);
    }

    /**
     * 示例一：每次使用都代理生成RSA的公私钥，用作demo展示。
     *
     * @param rsaEncodeBit
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair newGenerateKeyPair(int rsaEncodeBit) throws NoSuchAlgorithmException {
        // 1. 创建 KeyPairGenerator，指定算法和密钥长度
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSAEncryptConstant.RSA_ALGORITHM_NAME);
        keyGen.initialize(rsaEncodeBit); // 或 1024、4096

        // 2. 生成密钥对
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 打印或保存密钥
        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        return new KeyPair(publicKey, privateKey);
    }

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
    public static KeyPair readKeyPairFromPemFile(String privateKeyFilePath, String publicKeyFilePath) throws Exception {
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

    public static void main(String[] args) throws Exception {
        // 1. 每次都新生成RSA公私钥
        KeyPair newGenerateRSAKeyPair = newGenerateKeyPair(RSAEncryptConstant.RSA_ENCODE_BIT);

        // 2. 给定私钥文件地址、推导公钥并返回keyPair
        String privateKeyFilePath = "/Users/zhaochensheng/Downloads/rsa_test/rsa_pkcs8.pem";
        KeyPair fileRSAKeyPair = RsaKeyPairExtractor.loadKeyPairFromPrivatePem(privateKeyFilePath);

        // 3. 给定公私钥文件内容、自动识别并读出区间内的公私钥Base64区块
        String pemFileContent = RSAEncryptConstant.SAMPLE_PRIVATE_KEY_FILE_CONTENT;
        String configBase64KeyContent = RsaKeyPairExtractor.extractPemBase64BlockFromFileContent(pemFileContent);

        System.out.println(configBase64KeyContent);

        // 4. 从config中读取configBase64KeyContent，进行实例化公私钥
        KeyPair configRSAKeyPair = RsaKeyPairExtractor.loadKeyPairFromConfigBase64Content(configBase64KeyContent);

        // 5. 准备待加密明文
        String plaintext = "Hello RSA! Use RSA algorithm to encrypt text. Use RSA algorithm to encrypt text. Use RSA algorithm to encrypt text.";

        // 5-1. 公钥加密
        String encryptedBase64 = encryptText(plaintext, configRSAKeyPair.getPublic());
        System.out.println("encryptedBase64: " + encryptedBase64);

        // 5-2. 私钥解密密文
        String decryptText = decryptText(encryptedBase64, configRSAKeyPair.getPrivate());
        System.out.println("decryptText: " + decryptText);

        // 6. 准备签名
        String signPlainText = "This is my signature!";

        // 6-1. 私钥签名
        String signature = signWithPrivateKey(signPlainText, configRSAKeyPair.getPrivate());
        System.out.println("签名（Base64）: " + signature);

        // 6-2. 公钥验签
        boolean isVerified = verifySignWithPublicKey(signPlainText, signature, configRSAKeyPair.getPublic());
        System.out.println("验签结果: " + isVerified);
    }

}