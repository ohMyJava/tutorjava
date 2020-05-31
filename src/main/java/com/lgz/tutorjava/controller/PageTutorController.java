package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.service.PageTutorService;
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
 * @date 2020/5/7 8:44
 * 前台页面家教中心控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/tutorPage")
public class PageTutorController {
    private final static Logger LOGGER= LoggerFactory.getLogger(PageTutorController.class);
    @Autowired
    private PageTutorService pageTutorService;

    @PostMapping("/getTutors")
    public Message getTutors(@RequestBody String json, HttpServletRequest request){
        Message msg = new Message();
        try {
            Map<String,Object> params= JsonUtil.jsonToMap(json);
            LOGGER.info(json);
            String able=params.get("able").toString();
            String school=params.get("school").toString();
            String location=params.get("location").toString();
            LOGGER.info(able);
            LOGGER.info(school);
            LOGGER.info(location);
            boolean flag = true;
            //如果token为空，则证明用户未登录，因此需对数据及参数进行处理
            if (request.getHeader("token")==null){
                able="";
                school="";
                location="";
                flag=false;
            }
            Integer page=(Integer) params.get("page");
            Integer limit=(Integer) params.get("limit");
            msg.setData(pageTutorService.getTutors(able,school,location,page,limit,flag));
            msg.setInfo("6666","家教列表获取成功");
        }catch (Exception e){
            msg.setInfo("7777","查询失败");
            LOGGER.info("家教信息查询出现异常："+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getOneTutor")
    public Message getOneTutor(Integer tutorId,HttpServletRequest request){
        Message msg = new Message();
        try {
            boolean flag = true;
            if (request.getHeader("token")==null){
                flag = false;
            }
            msg.setData(pageTutorService.getOneTutor(tutorId,flag));
            msg.setInfo("6666","查询成功");
            LOGGER.info("家教信息查询成功，家教ID="+tutorId);
        }catch (Exception e){
            msg.setInfo("7777","查询失败");
            LOGGER.info("家教信息查询出现异常:"+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getNumbers")
    public Message getNumbers(@Param("able") String able,@Param("school") String school,@Param("location") String location){
        Message msg = new Message();
        try {
            msg.setData(pageTutorService.getNumbers(able,school,location));
            msg.setInfo("6666","查询成功");
            LOGGER.info("获取家教数量成功！");
        }catch (Exception e){
            LOGGER.info("获取家教数量出现异常："+e.getMessage());
            msg.setInfo("7777","查询失败");
        }
        return msg;
    }

    @PostMapping("/invite")
    public Message invite(@RequestBody String json){
        Message msg = new Message();
        try {
            Map<String,Object> map = JsonUtil.jsonToMap(json);
            Integer result = pageTutorService.invite(map);
            if (result==1){
                msg.setInfo("6666","邀请家教成功，等待对方答复！");
                LOGGER.info("用户邀请对方成为家教成功！");
            }else{
                msg.setInfo("8888","邀请家教失败！");
            }
        }catch (Exception e){
            msg.setInfo("7777","邀请家教失败");
            LOGGER.warn("邀请家教过程出现异常："+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getUserStudent")
    public Message getUserStudent(String userName){
        Message msg = new Message();
        try {
            msg.setData(pageTutorService.getUserStudent(userName));
            msg.setInfo("6666","查询用户学生列表成功！");
            LOGGER.info("查询用户学生列表成功");
            LOGGER.info(JsonUtil.objectToJson(msg));
        }catch (Exception e){
            LOGGER.warn("查询用户学生列表出现异常！"+e.getMessage());
            msg.setInfo("7777","查询用户列表出现异常！");
        }
        return msg;
    }
}
