package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.Admin;
import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.service.AdminService;
import com.lgz.tutorjava.utils.JsonUtil;
import com.lgz.tutorjava.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("getComments")
    public Message getComments(@Param("page") Integer page,@Param("limit") Integer limit){
        Message msg = Message.getInstance();
        List<Comment> comments = adminService.getComments(page,limit);
        if (comments.size()>0){
            msg.setData(comments);
            msg.setInfo("6666","获取用户留言成功！");
            LOGGER.info("获取用户留言成功");
        }else {
            msg.setInfo("8888","无未审批留言");
        }
        return msg;
    }

    @GetMapping("getCommentsNum")
    public Message getCommentsNum(){
        Message msg = Message.getInstance();
        Integer count = adminService.getCommentsNum();
        msg.setData(count);
        msg.setInfo("6666","获取数量成功");
        LOGGER.info("获取数量成功："+count);
        return msg;
    }

    @PostMapping("answerComment")
    public Message answerComment(@RequestBody String json){
        Message msg = Message.getInstance();
        Map map = JsonUtil.jsonToMap(json);
        Integer commentId = (Integer) map.get("commentId");
        String answer = map.get("answer").toString();
        LOGGER.info(commentId+"-----"+answer);
        int result = adminService.answerComment(commentId,answer);
        if (result == 1){
            msg.setInfo("6666","回复成功！");
            LOGGER.info("回复留言成功");
        }else {
            msg.setInfo("7777","回复失败！");
            LOGGER.info("回复留言失败");
        }

        return msg;
    }


}
