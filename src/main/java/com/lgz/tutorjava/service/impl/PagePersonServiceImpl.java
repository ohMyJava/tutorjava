package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.PagePersonMapper;
import com.lgz.tutorjava.dao.PageStudentMapper;
import com.lgz.tutorjava.dao.StudentMapper;
import com.lgz.tutorjava.dao.TutorMapper;
import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.model.Order;
import com.lgz.tutorjava.model.User;
import com.lgz.tutorjava.service.PagePersonService;
import com.lgz.tutorjava.utils.DateUtil;
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
        return pagePersonMapper.updateMyInfo(user);
    }

    @Override
    public User getMyInfo(Integer userID){
        return pagePersonMapper.getMyInfo(userID);
    }

    @Override
    public List<Map<String,Object>> commentInfo(Integer userId){
        List<Comment> comments = pagePersonMapper.commentInfo(userId);
        List<Map<String,Object>> result = new ArrayList<>();
        Integer commentType = null;
        Map<String,Object> map = new HashMap<>();

        for (Comment comment:comments){
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
            result.add(map);
        }
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
    public Boolean agree(Integer id){
        Boolean flag = false;
        //设为已读
        if (pagePersonMapper.setInviteIsRead(id)==1){
            LOGGER.info("邀请同意表中记录设为已读状态，ID="+id);
            flag = true;
        }else {
            LOGGER.info("邀请同意表中记录设为已读状态失败，ID="+id);
            flag = false;
            return flag;
        }

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
        inviteInfo.put("time",DateUtil.currDate());
        inviteInfo.put("type",2);
        if (pageStudentMapper.invite(inviteInfo)==1){
            flag = true;
        }else {
            flag = false;
            return flag;
        }

        //生成订单


        return flag;
    }
}
