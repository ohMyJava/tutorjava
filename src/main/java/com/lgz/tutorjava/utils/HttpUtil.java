package com.lgz.tutorjava.utils;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lgz
 * @date 2020/5/20 15:37
 */
public class HttpUtil {
    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return token;
    }
}
