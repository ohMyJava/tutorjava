package com.lgz.tutorjava.utils;

/**
 * @author lgz
 * @date 2020/4/17 9:54
 * 信息类，用户向前端返回信息前的封装过程
 */
public class Message {
    private String code;
    private String message;
    private Object data;

    private static Message instance=new Message();
    private Message(){}
    public static Message getInstance(){
        return instance;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setInfo(String code,String message){
        this.code=code;
        this.message=message;
    }
}
