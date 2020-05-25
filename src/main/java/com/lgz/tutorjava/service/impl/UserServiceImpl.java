package com.lgz.tutorjava.service.impl;

import com.lgz.tutorjava.config.RedisConfig;
import com.lgz.tutorjava.dao.UserMapper;
import com.lgz.tutorjava.model.User;
import com.lgz.tutorjava.service.UserService;
import com.lgz.tutorjava.utils.DateUtil;
import com.lgz.tutorjava.utils.MD5Util;
import com.lgz.tutorjava.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgz
 * @date 2020/3/30 21:55
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    /**
     * redis中存储的过期时间1800s
     */
    private static int ExpireTime = 1800;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Map<String,Object> userLogin(Map<String,Object> userInfo){
        final String userType="1";
        final String adminType="2";
        Map<String,Object> loginInfo=new HashMap<>();
        String password=userInfo.get("password").toString();
        String type=userInfo.get("type").toString();

        if (userType.equals(type)){
            Map<String,Object> tempUser =userMapper.userLoginCheck(userInfo.get("username").toString());
            loginInfo=checkLogin(tempUser,password);
        }else if (adminType.equals(type)){
            Map<String,Object> tempAdmin =userMapper.adminLoginCheck(userInfo.get("username").toString());
            loginInfo=checkLogin(tempAdmin,password);
        }
        loginInfo.put("type",type);
        return loginInfo;
    }

    private Map<String,Object> checkLogin(Map<String,Object> user,String password){
        Map<String,Object> loginInfo = new HashMap<>();
        String flag="false";
        String username = user.get("userName").toString();
        try {
            if (user.get("password").toString().equals(password)){
                loginInfo.put("message","登录成功！");
                flag="true";
                loginInfo.put("flag",flag);
                loginInfo.put("username",username);
                loginInfo.put("id",user.get("id"));
                String token = MD5Util.enToken(username);
                loginInfo.put("token",token);
                //token信息存入redis数据库，失效时间为6000s
                redisUtil.set(username,token);
                redisUtil.expire(username,6000);
            }else {
                loginInfo.put("message","密码错误！");
                loginInfo.put("flag",flag);
            }
        }catch (NullPointerException e){
            System.err.println(e);
            loginInfo.put("message","用户名不存在");
            loginInfo.put("flag",flag);
        }catch (Exception e){
            System.err.println("其他错误");
            loginInfo.put("message","客户端操作异常");
            loginInfo.put("flag",flag);
        }
        return loginInfo;
    }

    @Override
     public int register(User user){
        Date date=new Date(System.currentTimeMillis());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setRegTime(df.format(date));
        try {
            user.setPassword(MD5Util.md5(user.getPassword(),"aaa"));
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("md5加密用户密码异常");
        }
        return userMapper.register(user);
    }

    @Override
    public int userNameCheck(String userName){
        return userMapper.userNameCheck(userName);
    }

    @Override
    public List getUsernameList(){
        return userMapper.getUsernameList();
    }

    @Override
    public int addUser(User user){
        user.setRegTime(DateUtil.currDate());
        return userMapper.addUser(user);
    }

    @Override
    public List<User> getUsers(String condition,Integer page,Integer limit){
        page=(page-1)*limit;
        return userMapper.getUsers(condition,limit,page);
    }

    @Override
    public Integer getNumbers(String condition){
        return userMapper.getNumbers(condition);
    }

    @Override
    public Integer delUser(String delList){
        return userMapper.delUser(DateUtil.currDate(),delList);
    }

    @Override
    public void loginOut(String userName){
        redisUtil.del(userName);
    }

}
