package com.lgz.tutorjava.service;

import com.lgz.tutorjava.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/3/30 21:53
 */
public interface UserService {
    /**
     * 用户登录验证
     * @param user 用户对象
     * @return 验证信息
     */
    Map<String,Object> userLogin(Map<String,Object> user);

    int register(User user);

    int userNameCheck(String userName);

    List getUsernameList();

    int addUser(User user);
}
