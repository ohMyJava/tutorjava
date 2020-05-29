package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.Student;
import com.lgz.tutorjava.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/4/7 17:03
 */
@Repository
public interface StudentMapper {

    List<Map<String,Object>> getStudentList(@Param("limit") int limit,@Param("page") int page,@Param("condition") String condition);

    int delStudents(@Param("updateTime")String updateTime,@Param("delList") String delList);

    int addStudent(@Param("student")Student student);

    User findUserName(@Param("name") String name);

    int modifyStudent(@Param("student") Student student);

    Student getStudent(Integer studentId);

    int studentNumber(@Param("condition") String condition);

    String getStudentNameById(Integer studentId);

    Integer getUserIdByStudentId(Integer studentId);
}
