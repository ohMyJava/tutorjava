package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.service.PageStudentService;
import com.lgz.tutorjava.utils.JsonUtil;
import com.lgz.tutorjava.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/7 13:45
 * 前端页面学生中心控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/studentPage")
public class PageStudentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PageStudentController.class);
    @Autowired
    private PageStudentService pageStudentService;

    @PostMapping("/getStudents")
    public Message getStudents(@RequestBody String json, HttpServletRequest request){
        Message msg=Message.getInstance();
        try {
            Map<String,Object> map= JsonUtil.jsonToMap(json);
            String able=map.get("able").toString();
            String grade=map.get("grade").toString();
            String location=map.get("location").toString();
            boolean flag = true;
            //如果token为空，则证明用户未登录，因此需对数据及参数进行处理
            if (request.getHeader("token")!=null){
                able="";
                grade="";
                location="";
                flag=false;
            }
            Integer page=(Integer)map.get("page");
            Integer limit=(Integer)map.get("limit");

            msg.setData(pageStudentService.getStudents(able,grade,location,page,limit,flag));
            msg.setInfo("6666","查询所有学生成功");
            LOGGER.info("查询所有学生成功");
        }catch(Exception e){
            msg.setInfo("7777","查询所有学生出现异常");
            LOGGER.info("查询所有学生出现异常"+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getOneStudent")
    public Message getOneStudent(Integer studentId,HttpServletRequest request){
        Message msg = Message.getInstance();
        try {
            boolean flag =true;
            if (request.getHeader("token")==null){
                flag=false;
            }
            msg.setData(pageStudentService.getOneStudent(studentId,flag));
            msg.setInfo("6666","查询成功");
            LOGGER.info("查询学生信息成功"+studentId);
        }catch(Exception e){
            msg.setInfo("7777","查询学生信息出现异常");
            LOGGER.info("查询学生信息出现异常"+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getNumbers")
    public Message getNumbers(@Param("able") String able, @Param("grade") String grade, @Param("location") String location){
        Message msg = Message.getInstance();
        try {
            Integer count=pageStudentService.getNumbers(able,grade,location);
            msg.setData(count);
            msg.setInfo("6666","查询成功");
            LOGGER.info("查询学生数量成功："+count);
        }catch(Exception e){
            msg.setInfo("7777","查询学生数量出现异常");
            LOGGER.info("查询学生数量出现异常"+e.getMessage());
        }
        return msg;
    }

    @PostMapping("/invite")
    public Message invited(@RequestBody String json){
        Message msg = Message.getInstance();
        try {
            Map<String,Object> map = JsonUtil.jsonToMap(json);
            Integer result = pageStudentService.invite(map);
            if ( result == 1 ){
                msg.setInfo("6666","邀请成功，等待对方答复！");
                LOGGER.info("用户申请成为家教成功");
            }else {
                msg.setInfo("8888","邀请失败！");
                LOGGER.info("用户申请成为家教失败");
            }
        }catch (Exception e){
            msg.setInfo("6666","邀请过程出现异常！"+e.getMessage());
            LOGGER.warn("用户申请成为家教出现异常");
        }
        return msg;
    }

    @GetMapping("/getUserTutor")
    public Message getUserTutor(String userName){
        Message msg = Message.getInstance();
        try {
            msg.setData(pageStudentService.getUserTutor(userName));
            msg.setInfo("6666","获取用户家教列表成功！");
            LOGGER.info("获取用户家教列表成功!");
        }catch (Exception e){
            msg.setInfo("7777","获取用户家教列表出现异常"+e.getMessage());
        }
        return msg;
    }
}
