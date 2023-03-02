package com.qks.feignclient.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:25
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.BASIC;
    }
}
