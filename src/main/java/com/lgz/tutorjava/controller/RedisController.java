package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.utils.Message;
import com.lgz.tutorjava.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lgz
 * @date 2020/5/13 16:54
 */
@RestController
@CrossOrigin
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/set")
    public Message set(){
        Message msg = new Message();
        if (redisUtil.set("token","userId:1;key:tutor")){
            msg.setInfo("6666","OK");
        }else {
            msg.setInfo("7777","FAILED");
        }
        return msg;
    }

    @GetMapping("/get")
    public Message get(){
        Message msg = new Message();
        try {
            String token = redisUtil.get("token").toString();
            msg.setData(token);
            msg.setInfo("6666","OK");
        }catch (Exception e){
            msg.setInfo("7777","FAILED");
        }
        return msg;
    }
}
