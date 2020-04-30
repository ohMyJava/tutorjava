package com.lgz.tutorjava.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lgz
 * @date 2020/4/17 22:39
 * 日期工具类
 */
public class DateUtil {
    public static String currDate(){
        Date date=new Date(System.currentTimeMillis());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}
