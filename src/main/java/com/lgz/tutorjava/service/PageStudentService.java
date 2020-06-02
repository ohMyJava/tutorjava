package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Student;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/7 13:54
 */
public interface PageStudentService {
    List<Student> getStudents(String able,String grade,String location,
                              Integer page,Integer limit,boolean flag);

    Student getOneStudent(Integer studentId,boolean flag);

    Integer getNumbers(String able,String grade,String location);

    Integer invite(Map<String,Object> map);

    List<Map<String,Object>> getUserTutor(String userName);
}
