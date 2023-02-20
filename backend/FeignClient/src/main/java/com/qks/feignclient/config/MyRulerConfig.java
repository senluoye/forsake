package com.qks.feignclient.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-19 20:00
 */
@Configuration
public class MyRulerConfig {

    @Bean
    public IRule getRandomRule() {
        return new RandomRule();
    }
}