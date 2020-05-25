package com.lgz.tutorjava.config;

import com.lgz.tutorjava.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lgz
 * @date 2020/5/19 16:26
 */
public class Interceptor implements HandlerInterceptor {
    final Integer TIME = 86400;
    private final static Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userName = request.getHeader("userName");
        LOGGER.info("当前用户为："+userName+"，Token信息为："+token);
        Object redisInfo = redisUtil.get(userName);
        if (redisInfo==null){
            LOGGER.info("Token信息已失效，请求被驳回");
            return false;
        }else if (redisInfo.toString().equals(token)){
            redisUtil.expire(userName,TIME);
            return true;
        }else {
            LOGGER.info("Token信息有误，请求被驳回");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
