package com.lgz.tutorjava;

import com.lgz.tutorjava.controller.UserController;
import com.lgz.tutorjava.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lgz
 * @date 2020/4/29 16:31
 */
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void addUser(){
        User user=new User();
        for (int i=0;i<=30;i++){
            if (i<10){
                user.setUserName("190029000"+i);
            }else if (i>=10&&i<20) {
                user.setUserName("19002900" + i);
            }
            user.setPassword("123456");
            user.setAge(20);
            user.setName("用户"+i);
            user.setSex("男");
            user.setPhoneNumber("13322221111");
            userController.addUser(user);
            System.out.println("ok"+i);
        }
    }

    @Test
    void test(){
        String token = " tutor19 00290 ";
        String token1 = token.trim();
        System.out.println(token1);
        System.out.println(token1.substring(5));
    }
}
