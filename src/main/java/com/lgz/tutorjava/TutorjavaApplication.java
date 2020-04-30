package com.lgz.tutorjava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@MapperScan("com.lgz.tutorjava.dao")
@SpringBootApplication
public class TutorjavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorjavaApplication.class, args);
    }

}
