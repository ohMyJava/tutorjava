package com.lgz.tutorjava;

import com.lgz.tutorjava.controller.StudentController;
import com.lgz.tutorjava.model.Student;
import com.lgz.tutorjava.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lgz
 * @date 2020/4/29 16:16
 */
@SpringBootTest
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @Test
    void updateStudent(){
        Student student=new Student();
        for (int i=0;i<=34;i++){
            student.setStudentName("学生"+i);
            student.setStudentAge(20);
            student.setStudentSex("男");
            student.setUpdateTime(DateUtil.currDate());
            student.setUserName("1900290001");
            student.setExpectTutorAble("吃饭,睡觉,打豆豆");
            student.setExpectTutorLocation("山东省-青岛市-城阳区");
            student.setStudentGrade("小学一年级");
            student.setPhoneNumber("13055551111");
            student.setStudentHobby("我也不填");
            student.setStudentId(i);
            student.setStudentSubject("未分科");
            studentController.modifyStudent(student);
            System.out.println("ok"+i);
        }
    }
}
