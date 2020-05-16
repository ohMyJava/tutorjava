package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.PagePersonMapper;
import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.service.PagePersonService;
import com.lgz.tutorjava.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lgz
 * @date 2020/5/15 16:22
 */
@Service
public class PagePersonServiceImpl implements PagePersonService {

    @Autowired
    private PagePersonMapper pagePersonMapper;

    @Override
    public int addComment(Comment comment){
        comment.setSubmitTime(DateUtil.currDate());
        return pagePersonMapper.addComment(comment);
    }
}
