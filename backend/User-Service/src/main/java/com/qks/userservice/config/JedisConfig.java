package com.qks.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @ClassName JedisConfig
 * @Description Jedis
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-18 17:57
 */
@Configuration
public class JedisConfig {

    @Bean
    public Jedis jedis(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") int port) {
        return new Jedis(host, port);
    }
}
