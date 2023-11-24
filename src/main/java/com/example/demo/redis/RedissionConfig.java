package com.example.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
@Configuration
public class RedissionConfig
{
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Bean
    public RedissonClient getRedission(){
        String address = "redis://" + host + ":" + port;
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setPassword(password);
        config.setCodec(new StringCodec());
        return Redisson.create(config);
    }
}
