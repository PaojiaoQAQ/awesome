package com.example.demo.common.aspect;

import com.example.demo.common.exception.IdempotencyException;
import com.example.demo.common.intf.Idempotence;
import com.example.demo.common.utils.IdempotenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
@Aspect
@Slf4j
@Component
public class IdempotenceSupportAdvice
{
    @Resource
    private Idempotence idempotence;
    @Resource
    private IdempotenceUtil idempotenceUtil;

    /**
     * 拦截有@IdempotenceRequired 注解 的方法。
     */
    @Pointcut("@annotation(com.example.demo.common.annotation.IdempotenceRequired)")
    public void idempotenceMethod(){}

    @AfterThrowing(value = "idempotenceMethod()()",throwing = "e")
    public void afterThrowing(Throwable e){
        if(!(e instanceof IdempotencyException)) {
            // 从HTTP header中获取幂等号idempotenceId
            String idempotenceId = idempotenceUtil.getHeaderIdempotenceId();
            idempotence.record(idempotenceId, 1800L);
        }
    }

    @Around(value = "idempotenceMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 从HTTP header中获取幂等号idempotenceId
        String idempotenceId = idempotenceUtil.getHeaderIdempotenceId();
        if(StringUtils.isEmpty(idempotenceId)){
            //不存在幂等号则不进行额外操作
            return joinPoint.proceed();
        }
        // 前置操作 幂等号是否存在
        boolean existed = idempotence.check(idempotenceId);
        if (!existed) {
            throw new IdempotencyException("幂等校验不通过");
        }
        //删除幂等号
        idempotence.delete(idempotenceId);
        return joinPoint.proceed();
    }

}
