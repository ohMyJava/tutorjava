package com.lgz.tutorjava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lgz
 * @date 2020/3/31 12:52
 */
@RestController

public class IController {
    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello";
    }
}
