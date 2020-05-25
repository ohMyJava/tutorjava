package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.Student;

import java.util.List;

/**
 * @author lgz
 * @date 2020/5/7 13:54
 */
public interface PageStudentService {
    List<Student> getStudents(String able,String grade,String location,
                              Integer page,Integer limit,boolean flag);

    Student getOneStudent(Integer studentId,boolean flag);

    Integer getNumbers(String able,String grade,String location);
}
