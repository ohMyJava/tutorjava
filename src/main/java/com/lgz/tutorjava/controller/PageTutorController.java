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
        Message msg=Message.getInstance();
        try {
            Map<String,Object> params= JsonUtil.jsonToMap(json);
            String able=params.get("able").toString();
            String school=params.get("school").toString();
            String location=params.get("location").toString();
            boolean flag = true;
            //如果token为空，则证明用户未登录，因此需对数据及参数进行处理
            if (request.getHeader("token")!=null){
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
        Message msg=Message.getInstance();
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
        Message msg=Message.getInstance();
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
}
