package com.example.demo.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author tanyue
 * @Date 2023/11/14
 **/
@Component
@Slf4j
public class RedissonConfig
{
    @Value("${spring.redis.host}")
    private String redisAddr;
    @Value("${spring.redis.port}")
    private String redisPort;
    @Value("${spring.redis.password}")
    private String redisPwd;
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        String url = String.format("redis://%s:%s",redisAddr, redisPort);
        config.useSingleServer().setAddress(url).setPassword(redisPwd);
        return Redisson.create(config);
    }
}
