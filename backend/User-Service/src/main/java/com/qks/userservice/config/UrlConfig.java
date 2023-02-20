package com.qks.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName Dessert
 * @Description 拦截器
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-15 20:12
 */
@Configuration
public class UrlConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new MyHandlerInterceptor());

        //所有路径都被拦截
//        registration.addPathPatterns("/api/user/**");
    }
}
