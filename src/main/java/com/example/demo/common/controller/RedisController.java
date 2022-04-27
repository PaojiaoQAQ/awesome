package com.example.demo.common.controller;

import com.example.demo.common.annotation.IdempotenceRequired;
import com.example.demo.common.entity.Result;
import com.example.demo.common.pojo.RedisVO;
import com.example.demo.redis.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
@RestController
@RequestMapping("/api/redis")
public class RedisController
{
    @Resource
    private RedisUtil redisUtil;
    @GetMapping("/get/{key}")
    @IdempotenceRequired
    public Result<Object> getRedisValueByKey(@PathVariable String key){
        return new Result<>().success(redisUtil.get(key));
    }

    @PutMapping("/set")
    @IdempotenceRequired
    public Result<Object> setRedis(@RequestBody RedisVO redisVO){
        redisUtil.set(redisVO.getKey(),redisVO.getValue(),redisVO.getExpireTime());
        return new Result<>().success();
    }

}
