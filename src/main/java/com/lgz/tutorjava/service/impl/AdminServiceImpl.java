package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.AdminMapper;
import com.lgz.tutorjava.model.Admin;
import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.service.AdminService;
import com.lgz.tutorjava.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgz
 * @date 2020/4/29 15:07
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAdmins(Integer power){
        return adminMapper.getAdmins(power);
    }

    @Override
    public List<Comment> getComments(Integer page, Integer limit){
        List<Comment> comments = adminMapper.getComments((page-1)*limit,limit);
        //根据不同种类为其进行中文命名
        for (Comment comment:comments){
            switch (comment.getCommentType()){
                case 1:
                    comment.setCommentTypeName("错误提交");
                    break;
                case 2:
                    comment.setCommentTypeName("意见反馈");
                    break;
                case 3:
                    comment.setCommentTypeName("其他");
                    break;
                    default:
                        comment.setCommentTypeName("****");
            }
        }
        return comments;
    }

    @Override
    public Integer getCommentsNum(){
        return adminMapper.getCommentsNum();
    }

    @Override
    public Integer answerComment(Integer commentId,String answerContent){
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setCheckTime(DateUtil.currDate());
        comment.setAnswerContent(answerContent);
        return adminMapper.answerComment(comment);
    }
}
