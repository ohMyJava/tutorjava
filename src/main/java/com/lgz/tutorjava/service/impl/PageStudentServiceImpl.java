package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.PageStudentMapper;
import com.lgz.tutorjava.model.Student;
import com.lgz.tutorjava.service.PageStudentService;
import com.lgz.tutorjava.utils.DateUtil;
import com.lgz.tutorjava.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/7 13:54
 */
@Service
public class PageStudentServiceImpl implements PageStudentService {

    @Autowired
    private PageStudentMapper pageStudentMapper;

    @Override
    public List<Student> getStudents(String able,String grade,String location,
                                     Integer page,Integer limit,boolean flag){
        page=(page-1)*limit;
        List<Student> list = pageStudentMapper.getStudents(able,grade,location,page,limit);
        for (int i=0;i<list.size();i++){
            Student student=list.get(i);
            //姓名处理
            student.setStudentName(student.getStudentName().substring(0,1).concat("学员"));
            //如果未登录，则进行年级和所在地信息处理
            if (!flag){
                //年级处理
                student.setStudentGrade("****");
                //所在地点隐藏
                student.setExpectTutorLocation(
                        student.getExpectTutorLocation().substring(0,3).concat("**市**区"));
            }
        }
        return list;
    }

    @Override
    public Student getOneStudent(Integer studentId,boolean flag){
        Student student=pageStudentMapper.getOneStudent(studentId);
        //如果当前用户未登录，则隐藏教育信息、所在地；否则只隐藏姓名和手机号
        if (!flag){
            //教育信息隐藏
            String grade = student.getStudentGrade();
            student.setStudentGrade("****");
            //所在地点隐藏
            String location=student.getExpectTutorLocation();
            student.setExpectTutorLocation(location.substring(0,3).concat("**市**区"));
        }

        //隐藏联系方式、姓名
        StringBuilder phoneNumber=new StringBuilder(student.getPhoneNumber());
        student.setPhoneNumber(phoneNumber.replace(3,7,"****").toString());
        student.setStudentName(student.getStudentName().substring(0,1).concat("学员"));

        return student;
    }

    @Override
    public Integer getNumbers(String able,String grade,String location){
        return pageStudentMapper.getNumbers(able,grade,location);
    }

    @Override
    public Integer invite(Map<String,Object> map){
        map.put("time",DateUtil.currDate());
        return pageStudentMapper.invite(map);
    }

    @Override
    public String getUserTutor(String userName){
        return JsonUtil.objectToJson(pageStudentMapper.getUserTutor(userName));
    }
}
