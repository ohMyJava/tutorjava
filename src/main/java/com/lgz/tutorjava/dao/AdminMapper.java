package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Admin;
import com.lgz.tutorjava.model.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lgz
 * @date 2020/4/29 15:06
 */
@Repository
public interface AdminMapper {

    List<Admin> getAdmins(Integer power);

    List<Comment> getComments(@Param("page") Integer page,
                              @Param("limit") Integer limit);

    Integer getCommentsNum();

    Integer answerComment(Comment comment);

}
