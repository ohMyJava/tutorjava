package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.PagePersonMapper;
import com.lgz.tutorjava.model.Comment;
import com.lgz.tutorjava.service.PagePersonService;
import com.lgz.tutorjava.utils.DateUtil;
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

    @Autowired
    private PagePersonMapper pagePersonMapper;

    @Override
    public int addComment(Comment comment){
        comment.setSubmitTime(DateUtil.currDate());
        return pagePersonMapper.addComment(comment);
    }

    @Override
    public List<Map<String,Object>> replyInfo(Integer userId){
        List<Map<String,Object>> result = pagePersonMapper.replyInfo(userId);
        //1代表向学生申请，2代表向家教发出邀请
        for (Map<String,Object> map:result){
            if ((Integer) map.get("type")==1){
                map.put("title","家教申请");
            }else if((Integer)map.get("type")==2){
                map.put("title","学生邀请");
            }
        }
        return result;
    }
}
