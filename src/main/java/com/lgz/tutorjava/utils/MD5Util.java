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


    /**
     * 模拟加密
     * @param text 需加密文本
     * @return 加密后文本
     * @throws Exception 所有异常
     */
    public static String enToken(String text)throws Exception{
        String key = "tutor";
        return key+text;
    }

    public static String deToken(String token)throws Exception{
        return token.trim().substring(5);
    }
}
