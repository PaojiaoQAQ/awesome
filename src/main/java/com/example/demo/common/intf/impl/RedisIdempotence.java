package com.example.demo.common.intf.impl;

import com.example.demo.common.intf.Idempotence;
import org.springframework.stereotype.Component;
/**
 * @description
 * @author tanyue
 * @Date 2022/4/26
 **/
@Component
public class RedisIdempotence implements Idempotence
{

    @Override
    public boolean check(String idempotenceId)
    {
        return false;
    }
    @Override
    public void record(String idempotenceId)
    {

    }
    @Override
    public void record(String idempotenceId, Integer time)
    {

    }
    @Override
    public void delete(String idempotenceId)
    {

    }
}
