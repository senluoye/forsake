package com.qks.userservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName UserServiceApplication
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 14:49
 */
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = "com.qks.feignclient", defaultConfiguration = DefaultFeignConfiguration.class)
//@RibbonClient(name = "userservice", configuration = MyRulerConfig.class)
@MapperScan("com.qks.userservice.mapper")
@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
