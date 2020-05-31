package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.*;

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

    User getMyInfo(Integer userId);

    List<Map<String,Object>> commentInfo(Integer userId);

    Integer setIsRead(Integer commentId);

    boolean agree(Integer id);

    boolean refuse(Integer id);

    List<Map<String,Object>> getMyStudents(Integer userId);

    List<Map<String,Object>> getMyTutors(Integer userId);

    List<Map<String,Object>> getMyOrders(Integer userId);

    Integer setInviteInfoIsRead(Integer inviteId);
}
