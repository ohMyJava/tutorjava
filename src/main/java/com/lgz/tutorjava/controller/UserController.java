package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.User;
import com.lgz.tutorjava.service.UserService;
import com.lgz.tutorjava.utils.JsonUtil;
import com.lgz.tutorjava.utils.Message;
import com.sun.prism.impl.BaseMesh;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/3/30 21:49
 * 用户控制层：登录、注册
 */
@RestController
@RequestMapping({"/user"})
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    /**
     * 用户登录类
     * @param user 用户信息
     * @return msg
     */
    @PostMapping(value="/login")
    @ResponseBody
    @CrossOrigin
    public Map<String,Object> login(@RequestBody Map<String,Object> user){
        System.out.println(user.get("username"));
        System.out.println(user.get("password"));
        System.out.println(user.get("type"));
        Map<String,Object> info=new HashMap<>();
        info.put("username",user.get("username"));
        info.put("password",user.get("password"));
        info.put("type",user.get("type"));
        return userService.userLogin(info);
    }

    /**
     * 用户注册类
     * @param user 前端接收的Json对象
     * @return msg
     */
    @PostMapping(value = "/register")
    public Message register(User user){
        System.out.println(user);
        Message msg=Message.getInstance();
        try {
            if (userService.register(user)==1){
                LOGGER.info("注册成功");
                msg.setInfo("6666","注册成功！");
            }else {
                LOGGER.info("注册失败");
                msg.setInfo("7777","注册失败，请重试或联系管理员！");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("注册操作出现异常");
            msg.setInfo("7777","注册失败，请重试或联系管理员！");
        }
        return msg;
    }

    /**
     * 验证用户名是否重复
     * @param name 用户名
     * @return msg
     */
    @GetMapping("/uniqueUserName")
    public Message uniqueUserName(String name){
        Message msg=Message.getInstance();
        try {
            if (userService.userNameCheck(name)>0){
                msg.setInfo("8888","用户名不可用！");
                LOGGER.info("该用户名不可用"+name);
            }else {
                msg.setInfo("6666","用户名可用！");
                LOGGER.info("该用户名可用"+name);
            }
            LOGGER.info("检查用户名是否重复成功！");
        }catch (Exception e){
            e.printStackTrace();
            msg.setInfo("7777","操作处理出现异常！");
            LOGGER.info("检查用户名是否重复出现异常");
        }
        return msg;
    }

    @GetMapping("getUsernameList")
    public Message getUsernameList(){
        Message msg=Message.getInstance();
        try {
            msg.setData(userService.getUsernameList());
            msg.setInfo("6666","获取用户名列表成功！");
            LOGGER.info("获取用户名列表成功！");
        }catch (Exception e){
            LOGGER.info("获取用户名列表失败："+e.getMessage());
        }
        return msg;
    }

    @GetMapping("isTrueUserName")
    public Message isTrueUserName(String name){
        Message msg=Message.getInstance();
        try {
            if (userService.userNameCheck(name)>0){
                msg.setInfo("6666","用户名存在");
                LOGGER.info("验证成功！用户名存在！");
            }else {
                msg.setInfo("8888","用户名不存在");
                LOGGER.info("验证失败，用户名不存在");
            }
        }catch (Exception e){
            LOGGER.info("判断用户名出现错误："+e.getMessage());
            msg.setInfo("7777","判断用户名出现错误");
        }
        return msg;
    }

    @PostMapping("addUser")
    public Message addUser(@RequestBody User user){
        Message msg=Message.getInstance();
        try {
            int result=userService.addUser(user);
            if (result==1){
                msg.setInfo("6666","添加用户成功！");
                LOGGER.info("添加用户成功！");
            }else {
                msg.setInfo("8888","添加用户失败！");
                LOGGER.info("添加用户失败！");
            }
        }catch (Exception e){
            LOGGER.info("添加用户出现异常！"+e.getMessage());
            msg.setInfo("7777","添加用户出现异常！");
        }
        return msg;
    }

    @PostMapping("/getUsers")
    public Message getUsers(@RequestBody String json){
        Message msg = Message.getInstance();
        try {
            Map<String,Object> map = JsonUtil.jsonToMap(json);
            String condition=map.get("condition").toString();
            Integer limit = (Integer) map.get("limit");
            Integer page = (Integer) map.get("page");

            msg.setData(userService.getUsers(condition,page,limit));
            msg.setInfo("6666","获取所有用户列表成功！");
            LOGGER.info("获取所有用户列表成功！");
        }catch (Exception e){
            LOGGER.info("获取所有用户出现异常："+e.getMessage());
            msg.setInfo("7777","获取所有用户出现异常！");
        }
        return msg;
    }

    @GetMapping("getNumbers")
    public Message getNumbers(String condition){
        Message msg = Message.getInstance();
        try {
            Integer count = userService.getNumbers(condition);
            msg.setData(count);
            msg.setInfo("6666","查询用户数量成功！");
            LOGGER.info("查询用户数量成功！用户数量为："+count);
        }catch (Exception e){
            LOGGER.info("查询用户数量出现异常！");
            msg.setInfo("7777","查询用户数量出现异常!");
        }
        return msg;
    }

    @PostMapping("delUser")
    public Message delUser(@RequestBody String delList){
        Message msg = Message.getInstance();
        try {
            String list=JsonUtil.jsonToString(delList);
            if (userService.delUser(list)>0){
                msg.setInfo("6666","删除成功！");
                LOGGER.info("删除用户列表成功："+delList);
            }else {
                msg.setInfo("7777","删除失败！请重试！");
                LOGGER.info("删除用户列表失败");
            }

        }catch (Exception e){
            LOGGER.info("删除用户出现异常！"+e.getMessage());
            msg.setInfo("7777","删除用户出现异常!");
        }
        return msg;
    }

    @GetMapping("loginout")
    public Message loginOut(HttpServletRequest request){
        Message msg = Message.getInstance();
        try {
            String userName = request.getHeader("userName");
            userService.loginOut(userName);
            msg.setInfo("6666","退出成功！");
            LOGGER.info("用户"+userName+"退出成功！");
        }catch (Exception e){
            msg.setInfo("7777","退出失败，请重试！");
            LOGGER.info("用户退出登录操作出现异常"+e.getMessage());
        }
        return msg;
    }
}
