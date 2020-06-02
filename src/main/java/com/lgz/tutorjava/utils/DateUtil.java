package com.lgz.tutorjava.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    public static String orderNumber(){
        Date date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random(100);
        int randomNumber = random.nextInt(100);
        return "A"+df.format(date)+randomNumber;
    }
}
