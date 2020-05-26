package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/15 16:15
 */
@Repository
public interface PagePersonMapper {
    
    int addComment(@Param("comment") Comment comment);

    List<Map<String,Object>> replyInfo(Integer userId);
}
