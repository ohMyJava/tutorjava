package com.lgz.tutorjava.controller;

import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.model.User;
import com.lgz.tutorjava.service.PagePersonService;
import com.lgz.tutorjava.utils.JsonUtil;
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

    @PostMapping("/addComment")
    public Message addComment(@RequestBody Comment comment){
        Message msg = new Message();
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
        Message msg = new Message();
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
            msg.setInfo("7777","获取邀请答复消息时出现异常！");
            LOGGER.warn("获取邀请答复消息时出现异常"+e.getMessage());
        }
        return msg;
    }

    @PostMapping("/updateMyInfo")
    public Message updateMyInfo(@RequestBody User user){
        Message msg = new Message();
        try {
            int result = pagePersonService.updateMyInfo(user);
            if (result == 1){
                msg.setInfo("6666","更新个人信息成功！");
                LOGGER.info("更新个人信息成功"+user.getUserId());
            }else {
                msg.setInfo("8888","更新个人信息失败！");
                LOGGER.info("更新个人信息失败"+user.getUserId());
            }
        }catch (Exception e){
            msg.setInfo("7777","更新个人信息出现异常");
            LOGGER.warn("更新个人信息出现异常"+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getMyInfo")
    public Message getMyInfo(Integer userId){
        Message msg = new Message();
        msg.setData(pagePersonService.getMyInfo(userId));
        msg.setInfo("6666","获取用户信息成功");
        LOGGER.info("获取用户信息成功，用户ID为："+userId);
        return msg;
    }

    @GetMapping("/commentInfo")
    public Message commentInfo(Integer userId){
        Message msg = new Message();
        try {
            List<Map<String,Object>> comments = pagePersonService.commentInfo(userId);
            if (!comments.isEmpty()){
                msg.setData(comments);
                msg.setInfo("6666","获取未读留言回复信息成功");
                LOGGER.info("获取未读留言回复信息成功");
            }else {
                msg.setInfo("8888","无未读留言回复信息");
                LOGGER.info("无未读留言回复信息");
            }
        }catch (Exception e){
            msg.setInfo("7777","获取未读留言回复信息出现异常");
            LOGGER.info("获取未读留言回复信息出现异常"+e.getMessage());
        }
        return msg;
    }

    /**
     * 用户同意邀请后，此条消息设为已读，并生成新订单以及新的通知消息（给邀请方）
     * @param id 留言评论表主键id
     * @return msg
     */
    @GetMapping("/agree")
    public Message agree(Integer id){
        Message msg = new Message();
        try {
            if (pagePersonService.agree(id)){
                msg.setInfo("6666","已同意");
                LOGGER.info("已同意，邀请Id为："+id);
            }else {
                msg.setInfo("8888","用户同意邀请过程出现问题");
                LOGGER.info("用户同意邀请过程出现问题");
            }
        }catch (Exception e){
            msg.setInfo("7777","用户同意邀请过程出现异常");
            LOGGER.warn("用户同意邀请过程出现异常"+e.getMessage());
        }
        return msg;
    }

    /**
     * 用户拒绝后，此条消息设为已读，生成新消息给邀请方，告知其被拒绝
     * @param id 邀请答复表主键id
     * @return msg
     */
    @GetMapping("/refuse")
    public Message refuse(Integer id){
        Message msg = new Message();
        try {
            if (pagePersonService.refuse(id)){
                msg.setInfo("6666","已拒绝");
                LOGGER.info("邀请被拒绝，邀请Id为："+id);
            }else {
                msg.setInfo("8888","用户拒绝邀请过程出现问题");
                LOGGER.info("用户拒绝邀请过程出现问题");
            }
        }catch (Exception e){
            msg.setInfo("7777","用户拒绝邀请过程出现异常");
            LOGGER.warn("用户拒绝邀请过程出现异常"+e.getMessage());
        }
        return msg;
    }

    /**
     * 根据id将留言评论表中对应记录is_read字段值设为1（已读）
     * @param id 留言评论ID
     * @return msg
     */
    @GetMapping("/setIsRead")
    public Message setIsRead(Integer id){
        Message msg = new Message();
        try {
            Integer result = pagePersonService.setIsRead(id);
            if (result == 1){
                msg.setInfo("6666","更新为已读属性成功");
                LOGGER.info("更新为已读属性成功：ID="+id);
            }else {
                msg.setInfo("8888","更新为已读属性失败");
                LOGGER.info("更新为已读属性失败：ID="+id);
            }
        }catch (Exception e){
            msg.setInfo("7777","更新为已读属性出现异常");
            LOGGER.info("更新为已读属性出现异常,ID="+id+"，异常为："+e.getMessage());
        }
        return msg;
    }

    /**
     * 获取用户对应学生列表
     * @param userId 用户id
     * @return msg
     */
    @GetMapping("/getMyStudents")
    public Message getMyStudents(Integer userId){
        Message msg = new Message();
        try {
            msg.setData(pagePersonService.getMyStudents(userId));
            msg.setInfo("6666","查询用户关联的学生信息成功");
            LOGGER.info("查询用户关联的学生信息成功，用户id为："+userId);
        }catch (Exception e){
            msg.setInfo("7777","查询用户关联的学生信息失败");
            LOGGER.info("查询用户关联的学生信息出现异常："+e.getMessage());
        }
        return msg;
    }

    /**
     * 获取用户对应家教列表
     * @param userId 用户id
     * @return msg
     */
    @GetMapping("/getMyTutors")
    public Message getMyTutors(Integer userId){
        Message msg = new Message();
        try {
            msg.setData(pagePersonService.getMyTutors(userId));
            msg.setInfo("6666","查询用户关联的家教信息成功");
            LOGGER.info("查询用户关联的家教信息成功，用户id为："+userId);
        }catch (Exception e){
            msg.setInfo("7777","查询用户关联的家教信息失败");
            LOGGER.info("查询用户关联的家教信息出现异常："+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/getMyOrders")
    public Message getMyOrders(Integer userId){
        Message msg = new Message();
        try {
            msg.setData(pagePersonService.getMyOrders(userId));
            msg.success("查询用户订单成功");
            LOGGER.info("查询用户订单成功！用户id="+userId);
        }catch (Exception e){
            msg.error("查询用户订单失败");
            LOGGER.warn("查询用户订单出现异常："+e.getMessage());
        }
        return msg;
    }

    @GetMapping("/setInviteInfoIsRead")
    public Message setInviteInfoIsRead(Integer id){
        Message msg = new Message();
        try {
            Integer result = pagePersonService.setInviteInfoIsRead(id);
            if (result == 1){
                msg.setInfo("6666","更新为已读属性成功");
                LOGGER.info("更新为已读属性成功：ID="+id);
            }else {
                msg.setInfo("8888","更新为已读属性失败");
                LOGGER.info("更新为已读属性失败：ID="+id);
            }
        }catch (Exception e){
            msg.setInfo("7777","更新为已读属性出现异常");
            LOGGER.info("更新为已读属性出现异常,ID="+id+"，异常为："+e.getMessage());
        }
        return msg;
    }
}
