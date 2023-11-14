package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author tanyue
 * @Date 2023/11/14
 **/
@RequestMapping("/redis")
@RequiredArgsConstructor
@Slf4j
@RestController
public class RedisController
{
    private final RedissonClient redissonClient;
    @RequestMapping("/test")
    public void testRedisson() throws InterruptedException
    {
        RLock lock = redissonClient.getLock("test_lock");
        boolean lock1 = lock.tryLock(5, 60,TimeUnit.SECONDS);
        log.info("lock1:{}", lock1);
        boolean lock2 = lock.tryLock(5, 120,TimeUnit.SECONDS);
        log.info("lock2:{}", lock2);
    }
}
