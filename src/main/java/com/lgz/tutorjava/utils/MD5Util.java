package com.lgz.tutorjava.utils;


import org.springframework.util.DigestUtils;
/**
 * @author lgz
 * @date 2020/4/17 14:25
 * MD5加密解密工具类
 * 使用Spring框架自带的md5工具
 */
public class MD5Util {
    public static String md5(String text, String key) throws Exception {
        // 加密后的字符串
        return DigestUtils.md5DigestAsHex((text + key).getBytes());
    }
}
