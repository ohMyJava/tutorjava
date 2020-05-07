package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.Tutor;
import com.lgz.tutorjava.service.TutorService;
import com.lgz.tutorjava.utils.JsonUtil;
import com.lgz.tutorjava.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lgz
 * @date 2020/4/23 15:55
 * 后台管理-家教管理
 */
@RestController
@CrossOrigin
@RequestMapping("tutor")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TutorController.class);

    @GetMapping("getTutors")
    public Message getTutors(Integer limit,Integer page,String condition){
        Message msg=Message.getInstance();
        try{
            msg.setData(tutorService.getTutors(limit,page,condition));
            msg.setInfo("6666","查询家教列表成功");
        }catch (Exception e){
            LOGGER.info("获取家教列表出现异常"+e.getMessage());
            msg.setInfo("7777","查询列表失败，请联系管理员");
        }
        return msg;
    }

    @GetMapping("tutorNumber")
    public Message tutorNumber(String condition){
        Message msg=Message.getInstance();
        try {
            msg.setData(tutorService.tutorNumber(condition));
            msg.setInfo("6666","查询家教数量成功");

        }catch (Exception e){
            LOGGER.info("获取家教总数出现异常"+e.getMessage());
            msg.setInfo("7777","获取失败，请联系管理员");
        }
        return msg;
    }

    @PostMapping("delTutor")
    public Message delTutor(@RequestBody String delList){
        Message msg=Message.getInstance();
        System.out.println(delList);
        try {
            if (tutorService.delTutor(delList)>0){
                msg.setInfo("6666","删除成功！");
                LOGGER.info("删除家教列表成功："+delList);
            }else {
                msg.setInfo("7777","删除失败！请重试！");
                LOGGER.info("删除家教列表失败");
            }
        }catch (Exception e){
            LOGGER.info("删除家教列表出现异常"+e.getMessage());
            msg.setInfo("8888","删除家教列表失败");
        }
        return msg;
    }

    @GetMapping("getOneTutor")
    public Message getOneTutor(Integer tutorId){
        Message msg=Message.getInstance();
        try {
            /*如果查询不到，没做判断*/
            msg.setData(tutorService.getOneTutor(tutorId));
            msg.setInfo("6666","查询家教信息成功");
            LOGGER.info("查询家教信息成功!tutorID="+tutorId);
        }catch (Exception e){
            msg.setInfo("7777","查询家教信息出现异常");
            LOGGER.info("查询家教信息出现异常"+e.getMessage());
        }
        return msg;
    }

    @PostMapping("addTutor")
    public Message addTutor(@RequestBody Tutor tutor){
        Message msg=Message.getInstance();
        try {
            if (tutorService.addTutor(tutor)==1){
                msg.setInfo("6666","添加家教成功！");
                LOGGER.info("添加家教成功！"+tutor.getTutorName());
            }else {
                msg.setInfo("8888","添加家教失败！");
                LOGGER.info("添加家教失败！"+tutor.getTutorName());
            }
        }catch (Exception e){
            msg.setInfo("7777","添加家教失败！");
            LOGGER.info("添加家教失败！"+e.getMessage());
        }
        return msg;
    }

    @PostMapping("modifyTutor")
    public Message modifyTutor(@RequestBody Tutor tutor){
        Message msg=Message.getInstance();
        try {
            if (tutorService.modifyTutor(tutor)==1){
                msg.setInfo("6666","修改家教信息成功！");
                LOGGER.info("修改家教信息成功！"+tutor.getTutorName());
            }else {
                msg.setInfo("8888","修改家教信息失败！");
                LOGGER.info("修改家教信息失败！"+tutor.getTutorName());
            }
        }catch (Exception e){
            msg.setInfo("7777","修改家教信息出现异常");
            LOGGER.info("修改家教信息出现异常"+e.getMessage());
        }
        return msg;
    }
}
