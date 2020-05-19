package com.lgz.tutorjava.config;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //放行请求集合
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("/studentPage/*");
        excludePatterns.add("/tutorPage/*");
        excludePatterns.add("/articlePage/*");

        registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns(excludePatterns);
    }
}
