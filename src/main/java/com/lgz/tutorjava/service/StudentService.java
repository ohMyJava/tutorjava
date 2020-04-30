package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Student;
import com.lgz.tutorjava.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/4/7 17:06
 */
public interface StudentService {
     List<Map<String,Object>> getStudentList(Integer limit,Integer page,String condition);

     int delStudents(String delList);

     int addStudent(Student student);

     User getBindingUser(String name);

     int modifyStudent(Student student);

     Student getStudent(Integer id);

     int studentNumber(String condition);
}
