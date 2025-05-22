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

    public static void main(String[] args) throws Exception {
        // 1. 每次都新生成RSA公私钥
        KeyPair newGenerateRSAKeyPair = newGenerateKeyPair(RSAEncryptConstant.RSA_ENCODE_BIT);

        // 2. 给定私钥文件地址、推导公钥并返回keyPair
        String privateKeyFilePath = "/Users/zhaochensheng/Downloads/rsa_test/rsa_pkcs8_pri.pem";
        KeyPair fileRSAKeyPair = RsaKeyPairExtractor.loadKeyPairFromPrivatePem(privateKeyFilePath);

        // 3. 都从磁盘上文件读取公私钥 (用来校验)
        String KeyPairPrivateKeyFilePath = "/Users/zhaochensheng/Downloads/rsa_test/rsa_pkcs8_pri.pem";
        String KeyPairPublicKeyFilePath = "/Users/zhaochensheng/Downloads/rsa_test/rsa_pkcs8_pub.pem";
        KeyPair bothFileRSAKeyPair = RsaKeyPairExtractor.loadKeyPairBothFromPemFile(KeyPairPrivateKeyFilePath, KeyPairPublicKeyFilePath);

        // 3. 给定公私钥文件内容、自动识别并读出区间内的公私钥Base64区块
        String pemFileContent = RSAEncryptConstant.SAMPLE_PRIVATE_KEY_FILE_CONTENT;
        String configBase64KeyContent = RsaKeyPairExtractor.extractPemBase64BlockFromFileContent(pemFileContent);

        System.out.println("读取到的配置内容, configBase64KeyContent: " + configBase64KeyContent);

        // 4. 从config中读取configBase64KeyContent，进行实例化公私钥
        KeyPair configRSAKeyPair2 = RsaKeyPairExtractor.loadKeyPairFromConfigBase64Content(configBase64KeyContent);

        // 5. 准备待加密明文
        String plaintext = "Hello RSA! Use RSA algorithm to encrypt text. Use RSA algorithm to encrypt text. Use RSA algorithm to encrypt text.";

        // 5-1. 公钥加密
        String encryptedBase64 = encryptText(plaintext, bothFileRSAKeyPair.getPublic());
        System.out.println("加密后的base64字符串， encryptedBase64: " + encryptedBase64);

        // 5-2. 私钥解密密文
        String decryptText = decryptText(encryptedBase64, bothFileRSAKeyPair.getPrivate());
        System.out.println("解密后的字符串, decryptText: " + decryptText);

        // 6. 准备签名
        String signPlainText = "This is my signature!";

        // 6-1. 私钥签名
        String signature = signWithPrivateKey(signPlainText, bothFileRSAKeyPair.getPrivate());
        System.out.println("签名（Base64）: " + signature);

        // 6-2. 公钥验签
        boolean isVerified = verifySignWithPublicKey(signPlainText, signature, bothFileRSAKeyPair.getPublic());
        System.out.println("验签结果: " + isVerified);
    }

}