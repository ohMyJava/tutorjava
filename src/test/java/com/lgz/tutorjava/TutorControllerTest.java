package com.lgz.tutorjava;

import com.lgz.tutorjava.controller.StudentController;
import com.lgz.tutorjava.controller.TutorController;
import com.lgz.tutorjava.model.Tutor;
import com.lgz.tutorjava.utils.DateUtil;
import com.lgz.tutorjava.utils.Message;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lgz
 * @date 2020/4/29 9:28
 */
@SpringBootTest
class TutorControllerTest {
    @Autowired
    private TutorController tutorController;
    @Autowired
    private StudentController studentController;

    private Message message;

    @Test
    void getTutors(){
//        Message tutors=tutorController.getTutors(10,1,"张三");
        Message tutors=tutorController.getTutors(10,1,"刘");
        System.out.println(tutors.getData());
    }

    @Test
    void tutorNumber(){
        String condition="";
//        String condition="刘";
        Message tutors=tutorController.tutorNumber(condition);
        System.out.println(tutors.getData());
    }

    @Test
    void delTutor(){
        String delList="[1]";
        Message message=tutorController.delTutor(delList);
        System.out.println(message);
    }

    @Test
    void getOneTutor(){
        /*Integer id=1;
        Message message=tutorController.getOneTutor(id);
        System.out.println(message.getData();*/
    }

    @Test
    void addTutor(){
        Tutor tutor=new Tutor();
        for (int i=1;i<31;i++){
            tutor.setTutorName("家教"+i);
            tutor.setTutorAge(20);
            tutor.setTutorSex("男");
            tutor.setRegTime(DateUtil.currDate());
            tutor.setPhoneNumber("13300000000");
            tutor.setTutorEducation("大四");
            tutor.setTutorGoodSubjects("吃饭,睡觉,打豆豆");
            tutor.setTutorHobby("可不填");
            tutor.setTutorSchool("**大学");
            tutor.setTutorLocation("山东省青岛市李沧区");
            tutor.setUserName("1900290001");
            tutorController.addTutor(tutor);
            System.out.println("ok"+i);
        }

    }
}
