package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.*;
import com.lgz.tutorjava.model.*;
import com.lgz.tutorjava.service.PagePersonService;
import com.lgz.tutorjava.utils.DateUtil;
import com.lgz.tutorjava.utils.JsonUtil;
import com.lgz.tutorjava.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/15 16:22
 */
@Service
public class PagePersonServiceImpl implements PagePersonService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PagePersonServiceImpl.class);

    @Autowired
    private PagePersonMapper pagePersonMapper;
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PageStudentMapper pageStudentMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addComment(Comment comment){
        comment.setSubmitTime(DateUtil.currDate());
        return pagePersonMapper.addComment(comment);
    }

    @Override
    public List<Map<String,Object>> replyInfo(Integer userId){
        List<Map<String,Object>> result = pagePersonMapper.replyInfo(userId);
        //1代表新的邀请，2代表新的答复
        for (Map<String,Object> map:result){
            LOGGER.info(JsonUtil.objectToJson(map.get("type")));
            if ((Integer) map.get("type")==1){
                map.put("title","新的邀请");
            }else if((Integer)map.get("type")==2){
                map.put("title","新的答复");
            }
        }
        return result;
    }

    @Override
    public int updateMyInfo(User user){
        user.setUpdateTime(DateUtil.currDate());
        try {
            user.setPassword(MD5Util.md5(user.getPassword(),"aaa"));
        }catch (Exception e){
            LOGGER.warn("密码加密失败");
            //默认设为123456
            user.setPassword("93a9e5bb1d598a453606e890f72bd393");
        }
        return pagePersonMapper.updateMyInfo(user);
    }

    @Override
    public User getMyInfo(Integer userId){
        return pagePersonMapper.getMyInfo(userId);
    }

    @Override
    public List<Map<String,Object>> commentInfo(Integer userId){
        List<Comment> comments = pagePersonMapper.commentInfo(userId);
        LOGGER.info("从数据库取得的数据：");
        LOGGER.info(JsonUtil.objectToJson(comments));
        List<Map<String,Object>> result = new ArrayList<>();
        Integer commentType = null;
        Map<String,Object> map = null;

        for (Comment comment:comments){
            map = new HashMap<>();
            commentType = comment.getCommentType();
            switch (commentType){
                case 1:
                    map.put("title","【错误提交】"+comment.getCommentContent());
                    break;
                case 2:
                    map.put("title","【意见反馈】"+comment.getCommentContent());
                    break;
                case 3:
                    map.put("title","【其他】"+comment.getCommentContent());
                    break;
                    default:
                        break;
            }
            map.put("id",comment.getCommentId());
            map.put("content",comment.getAnswerContent());
            LOGGER.info(JsonUtil.objectToJson(map));
            result.add(map);
        }
        LOGGER.info("处理完后的数据：");
        LOGGER.info(JsonUtil.objectToJson(result));
        return result;
    }

    /**
     * 将留言评论表里面is_read属性设置为1（已读）
     * @param commentId
     * @return
     */
    @Override
    public Integer setIsRead(Integer commentId){
        return pagePersonMapper.setIsRead(commentId);
    }

    /**
     * 用户同意后进行的操作：
     *      1.设为已读
     *      2.通知邀请方
     *      3.生成订单
     * @param id 订单
     * @return 1 或 0
     */
    @Override
    public boolean agree(Integer id){
        boolean flag;
        //设为已读
        flag = setInviteMessageIsRead(id);

        //通知邀请方
        Map<String,Object> inviteInfo = pagePersonMapper.getOneInviteRecord(id);
        Integer inviteUserId = (Integer) inviteInfo.get("inviteUserId");
        Integer invitedUserId = (Integer) inviteInfo.get("invitedUserId");
        //将邀请方和被邀请方调换位置，因为用户接受消息是以被邀请方为条件查询数据库的
        inviteInfo.put("invitedUserId",inviteUserId);
        inviteInfo.put("inviteUserId",invitedUserId);

        Integer studentId = (Integer) inviteInfo.get("studentId");
        Integer tutorId = (Integer) inviteInfo.get("tutorId");
        String studentName = studentMapper.getStudentNameById(studentId);
        String tutorName = tutorMapper.getTutorNameById(tutorId);
        String content = tutorName+"正式成为学员【"+studentName+"】的家教。";

        inviteInfo.put("content",content);
        inviteInfo.put("time",DateUtil.currDate());
        inviteInfo.put("type",2);
        inviteInfo.put("stuId",studentId);
        inviteInfo.put("isRead",0);
        if (pageStudentMapper.invite(inviteInfo)==1){
            flag = true;
        }else {
            flag = false;
            return flag;
        }

        //生成订单
        Order order = new Order();
        order.setSerial(DateUtil.orderNumber());
        order.setTime(DateUtil.currDate());
        order.setStudentUserId(studentMapper.getUserIdByStudentId(studentId));
        order.setStudentId(studentId);
        order.setTutorId(tutorId);
        order.setTutorUserId(tutorMapper.getUserIdByTutorId(tutorId));

        if (orderMapper.addOrder(order)==1){
            flag = true;
        }else {
            flag = false;
            LOGGER.warn("生成订单失败！");
            return flag;
        }
        return flag;
    }

    /**
     * 用户拒绝后，此条消息设为已读，生成新消息给邀请方，告知其被拒绝
     * @param id 邀请答复表主键id
     * @return boolean
     */
    @Override
    public boolean refuse(Integer id){
        //设置为已读状态
        boolean flag = setInviteMessageIsRead(id);
        //生成新消息给邀请方，告知其被拒绝
        flag = notifyTheInviter(id,false);
        return flag;
    }

    @Override
    public List<Map<String,Object>> getMyStudents(Integer userId){
        return studentMapper.getStudentsByUserId(userId);
    }

    @Override
    public List<Map<String,Object>> getMyTutors(Integer userId){
        return tutorMapper.getTutorsByUserId(userId);
    }

    @Override
    public List<Map<String,Object>> getMyOrders(Integer userId){
        return pagePersonMapper.getMyOrders(userId);
    }

    @Override
    public Integer setInviteInfoIsRead(Integer inviteId){
        return pagePersonMapper.setInviteIsRead(inviteId);
    }

    /**
     * 将invite表中消息设为已读
     * @return
     */
    private boolean setInviteMessageIsRead(Integer id){
        if (pagePersonMapper.setInviteIsRead(id)==1){
            LOGGER.info("邀请同意表中记录设为已读状态，ID="+id);
            return true;
        }else {
            LOGGER.info("邀请同意表中记录设为已读状态失败，ID="+id);
            return false;
        }
    }

    private boolean notifyTheInviter(Integer id,boolean isAgree){
        Map<String,Object> inviteInfo = pagePersonMapper.getOneInviteRecord(id);
        Integer inviteUserId = (Integer) inviteInfo.get("inviteUserId");
        Integer invitedUserId = (Integer) inviteInfo.get("invitedUserId");
        //将邀请方和被邀请方调换位置，因为用户接受消息是以被邀请方为条件查询数据库的
        inviteInfo.put("invitedUserId",inviteUserId);
        inviteInfo.put("inviteUserId",invitedUserId);

        Integer studentId = (Integer) inviteInfo.get("studentId");
        Integer tutorId = (Integer) inviteInfo.get("tutorId");
        String studentName = studentMapper.getStudentNameById(studentId);
        String tutorName = tutorMapper.getTutorNameById(tutorId);
        String content ;
        if (isAgree){
            content = tutorName+"正式成为学员【"+studentName+"】的家教。";
        }else {
            content = tutorName+"未能与【"+studentName+"】达成一致（被邀请方已拒绝）。";
        }
        inviteInfo.put("content",content);
        inviteInfo.put("time",DateUtil.currDate());
        inviteInfo.put("type",2);
        inviteInfo.put("stuId",inviteInfo.get("studentId"));
        inviteInfo.put("isRead",0);
        if (pageStudentMapper.invite(inviteInfo)==1){
            return true;
        }else {
            return false;
        }
    }
}
