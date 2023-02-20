package com.qks.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @ClassName Dessert
 * @Description 加密解密的工具类
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 23:25
 */
public class ComputeUtil {
    private final static byte[] AES_KEY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private static volatile AES aes;

    private static synchronized AES getAES() {
        if (aes == null) {
            synchronized (AES.class) {
                if (aes == null) {
                    aes = SecureUtil.aes(AES_KEY);
                }
            }
        }
        return aes;
    }

    /**
     * 加密
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        return getAES().encryptHex(password);
    }

    /**
     * 解密
     * @param string
     * @return
     */
    public static String decrypt(String string) {
        return getAES().decryptStr(string);
    }
}
