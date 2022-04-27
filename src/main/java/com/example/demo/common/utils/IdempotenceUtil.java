package com.example.demo.common.utils;

import cn.hutool.core.codec.Base64;
import com.example.demo.redis.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
@Component
public class IdempotenceUtil
{
    @Resource
    private RedisUtil redisUtil;

    /**
     * 生成幂等号
     * @return
     */
    public String generateId() {
        String uuid = UUID.randomUUID().toString();
        String uId= Base64.encode(uuid).toLowerCase();
        redisUtil.set(buildIdempotenceRedisKey(uId),"1",1800);
        return uId;
    }

    /**
     * 从Header里面获取幂等号
     * @return
     */
    public String getHeaderIdempotenceId(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String idempotenceId=request.getHeader("idempotenceId");
        return idempotenceId;
    }

    /**
     * 构建redis key
     * @param idempotenceId
     * @return
     */
    public static String buildIdempotenceRedisKey(String idempotenceId){
        return String.format("app:idempotent:%s",idempotenceId);
    }
}
