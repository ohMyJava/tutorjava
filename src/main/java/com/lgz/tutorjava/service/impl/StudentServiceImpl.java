package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.dao.StudentMapper;
import com.lgz.tutorjava.model.Student;
import com.lgz.tutorjava.model.User;
import com.lgz.tutorjava.service.StudentService;
import com.lgz.tutorjava.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/4/7 17:07
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Override
    public List<Map<String,Object>> getStudentList(Integer limit,Integer page,String condition){
        LOGGER.info("查询学生列表成功");
        return studentMapper.getStudentList(limit,(page-1)*limit,condition);

    }

    @Override
    public int delStudents(String delList){
        return studentMapper.delStudents(DateUtil.currDate(),delList);
    }

    @Override
    public int addStudent(Student student){
        Date date=new Date(System.currentTimeMillis());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        student.setRegTime(df.format(date));
        return studentMapper.addStudent(student);
    }

    @Override
    public User getBindingUser(String name){
        return studentMapper.findUserName(name);
    }

    @Override
    public int modifyStudent(Student student){
        student.setUpdateTime(DateUtil.currDate());
        return studentMapper.modifyStudent(student);
    }

    @Override
    public Student getStudent(Integer id){
        return studentMapper.getStudent(id);
    }

    @Override
    public int studentNumber(String condition){
        return studentMapper.studentNumber(condition);
    }
}
