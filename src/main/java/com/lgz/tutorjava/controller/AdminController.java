package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.Admin;
import com.lgz.tutorjava.service.AdminService;
import com.lgz.tutorjava.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lgz
 * @date 2020/4/29 15:04
 * 管理员控制层
 */
@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("getAdmins")
    public Message getAdmins(Integer power){
        Message msg=Message.getInstance();
        try {
            msg.setData(adminService.getAdmins(power));
            msg.setInfo("6666","查询管理员列表成功！");
            LOGGER.info("查询管理员列表成功");
        }catch (Exception e){
            LOGGER.info("查询管理员列表出现异常！"+e.getMessage());
            msg.setInfo("7777","查询管理员列表出现异常");
            LOGGER.info("查询管理员列表出现异常");
        }
        return msg;
    }



}
