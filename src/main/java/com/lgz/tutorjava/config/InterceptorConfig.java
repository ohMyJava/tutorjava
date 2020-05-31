package com.lgz.tutorjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lgz
 * @date 2020/5/19 16:28
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public Interceptor interceptor(){
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //放行请求集合
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("/user/register");
        excludePatterns.add("/user/uniqueUserName");
        excludePatterns.add("/user/login");
        excludePatterns.add("/studentPage/*");
        excludePatterns.add("/tutorPage/*");
        excludePatterns.add("/articlePage/*");


        registry.addInterceptor(interceptor()).addPathPatterns("/**").excludePathPatterns(excludePatterns);
    }
}
