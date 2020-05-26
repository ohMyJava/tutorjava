package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/15 16:22
 */
public interface PagePersonService {

    int addComment(Comment comment);

    List<Map<String,Object>> replyInfo(Integer userId);
}
