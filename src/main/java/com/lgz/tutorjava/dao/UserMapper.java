package com.lgz.tutorjava.dao;

import com.lgz.tutorjava.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/3/30 22:11
 */
@Repository
public interface UserMapper {
    Map<String,Object> userLoginCheck(String userName);


    Map<String,Object> adminLoginCheck(String userName);

    int register(@Param("user") User user);

    int userNameCheck(String userName);

    List<String> getUsernameList();

    int addUser(@Param("user") User user);
}
