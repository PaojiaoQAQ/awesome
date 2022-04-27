package com.example.demo.common.intf.impl;

import com.example.demo.common.intf.Idempotence;
import com.example.demo.common.utils.IdempotenceUtil;
import com.example.demo.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/26
 **/
@Component
public class RedisIdempotence implements Idempotence
{
    @Resource
    private RedisUtil redisUtil;
    @Override
    public boolean check(String idempotenceId)
    {
        return redisUtil.hasKey(IdempotenceUtil.buildIdempotenceRedisKey(idempotenceId));
    }
    @Override
    public void record(String idempotenceId)
    {
        redisUtil.set(IdempotenceUtil.buildIdempotenceRedisKey(idempotenceId),"1");
    }
    @Override
    public void record(String idempotenceId, Long expireTime)
    {
        redisUtil.set(IdempotenceUtil.buildIdempotenceRedisKey(idempotenceId),"1",expireTime);
    }
    @Override
    public void delete(String idempotenceId)
    {
        redisUtil.del(IdempotenceUtil.buildIdempotenceRedisKey(idempotenceId));
    }

}
