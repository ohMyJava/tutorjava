package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.model.Order;
import com.lgz.tutorjava.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/15 16:22
 */
public interface PagePersonService {

    int addComment(Comment comment);

    /**
     * 获取所有邀请回复相关且用户未读消息
     * @param userId 用户id
     * @return 消息集合
     */
    List<Map<String,Object>> replyInfo(Integer userId);

    int updateMyInfo(User user);

    User getMyInfo(Integer userID);

    List<Map<String,Object>> commentInfo(Integer userId);

    Integer setIsRead(Integer commentId);

    Boolean agree(Integer id);
}
