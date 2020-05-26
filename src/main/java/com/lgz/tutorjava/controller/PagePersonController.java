package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.service.PagePersonService;
import com.lgz.tutorjava.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/15 16:13
 * 个人中心控制层：
 *
 */
@RestController
@RequestMapping("/person")
@CrossOrigin
public class PagePersonController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PagePersonController.class);

    @Autowired
    private PagePersonService pagePersonService;

    @PostMapping("addComment")
    public Message addComment(@RequestBody Comment comment){
        Message msg = Message.getInstance();
        try {
            int result = pagePersonService.addComment(comment);
            if (result == 0){
                LOGGER.info("用户提交留言失败！");
                msg.setInfo("8888","留言失败!");
            }else {
                LOGGER.info("用户提交留言成功！");
                msg.setInfo("6666","留言成功!");
            }
        }catch (Exception e){
            LOGGER.info("用户提交留言出现异常："+e.getMessage());
            msg.setInfo("7777","留言失败!");
        }
        return msg;
    }

    @GetMapping("/replyInfo")
    public Message addComment(Integer userId){
        Message msg = Message.getInstance();
        try {
            List<Map<String,Object>> result = pagePersonService.replyInfo(userId);
            if (result.isEmpty()){
                msg.setInfo("8888","无最新邀请答复消息！");
                LOGGER.info("无最新邀请答复消息");
            }else {
                msg.setData(result);
                msg.setInfo("6666","获取邀请答复消息成功！");
                LOGGER.info("获取邀请答复消息成功");
            }
        }catch (Exception e){
            msg.setInfo("7777","获取邀请答复消息时出现异常！"+e.getMessage());
            LOGGER.warn("获取邀请答复消息时出现异常");
        }
        return msg;
    }

}
