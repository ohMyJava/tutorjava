package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/5/7 13:53
 */
@Repository
public interface PageStudentMapper {

    List<Student> getStudents(@Param("able") String able,
                              @Param("grade") String grade,
                              @Param("location") String location,
                              @Param("page") Integer page,
                              @Param("limit") Integer limit);

    Student getOneStudent(Integer studentId);

    Integer getNumbers(@Param("able") String able,
                       @Param("grade") String grade,
                       @Param("location") String location);

    Integer invite(Map<String,Object> map);

    List<Map<String,Object>> getUserTutor(String userName);
}
