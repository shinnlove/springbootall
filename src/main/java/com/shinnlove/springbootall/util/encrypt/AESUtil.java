/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Tony Zhao
 * @version $Id: AESUtil.java, v 0.1 2025-03-14 14:43 Tony Zhao Exp $$
 */
public class AESUtil {

    /** 加密算法 */
    private static final String ENCRYPT_ALGORITHM = "AES";

    /** 加密算法/加密模式/填充类型 本例采用AES加密，CBC 加密模式，PKCS5Padding填充 */
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";

    /** 阅文AES加密的密钥和IV */
    @Deprecated
    private static final String YUEWEN_AES_SECRET = "yuewensecretqddsip";

    public static void main(String[] args) {
        try {
            String plainText = "Hello, YinGe!";

            // 加密
            String encryptedText = encryptToUrlSafeBase64(plainText, YUEWEN_AES_SECRET);
            System.out.println("Encrypted (URL-safe Base64): " + encryptedText);

            // 解密
            String decryptedText = decryptFromUrlSafeBase64(encryptedText, YUEWEN_AES_SECRET);
            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptToUrlSafeBase64(String clearText, String pwd) {
        try {
            return encryptToUrlSafeBase64(clearText.getBytes(StandardCharsets.UTF_8), pwd.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return "";
        }
    }

    private static String encryptToUrlSafeBase64(byte[] clearTextBytes, byte[] pwdBytes) throws Exception {
        // 1 获取加密密钥
        SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOfRange(pwdBytes, 0, 16), ENCRYPT_ALGORITHM);

        // iv 偏移量
        IvParameterSpec ivSpec = new IvParameterSpec(Arrays.copyOfRange(pwdBytes, 0, 16));

        // 2 获取Cipher实例
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);

        // 3 初始化Cipher实例。设置执行模式以及加密密钥
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 4 执行加密
        byte[] cipherTextBytes = cipher.doFinal(clearTextBytes);

        // 5 返回URL安全的Base64编码的密文
        return Base64.getUrlEncoder().withoutPadding().encodeToString(cipherTextBytes);
    }

    public static String decryptFromUrlSafeBase64(String encryptedText, String pwd) {
        try {
            return decryptFromUrlSafeBase64(encryptedText, pwd.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return "";
        }
    }

    private static String decryptFromUrlSafeBase64(String encryptedText, byte[] pwdBytes) throws Exception {
        // 1 获取加密密钥
        SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOfRange(pwdBytes, 0, 16), ENCRYPT_ALGORITHM);

        // iv 偏移量
        IvParameterSpec ivSpec = new IvParameterSpec(Arrays.copyOfRange(pwdBytes, 0, 16));

        // 2 获取Cipher实例
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);

        // 3 初始化Cipher实例。设置执行模式以及加密密钥
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 4 对URL安全的Base64密文解码
        byte[] cipherTextBytes = Base64.getUrlDecoder().decode(encryptedText);

        // 5 执行解密
        byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}