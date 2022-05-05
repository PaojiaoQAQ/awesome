package com.example.demo.common.aspect;

import com.example.demo.common.annotation.Limit;
import com.example.demo.common.constant.LimitType;
import com.example.demo.redis.RedisUtil;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/29
 **/
@Slf4j
@Aspect
@Configuration
public class LimitInterceptor
{
    private static final String UNKNOWN = "unknown";
    @Resource
    private RedisUtil<String,Integer> redisUtil;

    @Around("execution(public * *(..)) && @annotation(com.example.demo.common.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        /**
         * 根据限流类型获取不同的key ,如果不传我们会以方法名作为key
         */
        String key;
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            case METHOD_NAME:
                String methodName = method.getName();
                key = StringUtils.upperCase(methodName);
                break;
            default:
                throw new RuntimeException("limitInterceptor - 无效的枚举值");
        }
        /**
         * 获取注解标注的 key，这个是优先级最高的，会覆盖前面的 key 值
         */
        Object[] args = pjp.getArgs();
        Annotation[][] paramAnnoAry = method.getParameterAnnotations();
        for (Annotation[] item : paramAnnoAry) {
            int paramIndex = ArrayUtils.indexOf(paramAnnoAry, item);
            for (Annotation anno : item) {
                if (anno instanceof Limit) {
                    Object arg = args[paramIndex];
                    if (arg instanceof String && StringUtils.isNotBlank((String) arg)) {
                        key = (String) arg;
                        break;
                    }
                }
            }
        }
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("limitInterceptor - key值不能为空");
        }
        String prefix = limitAnnotation.prefix();
        String[] keyAry = StringUtils.isBlank(prefix) ? new String[]{"limit", key} : new String[]{"limit", prefix, key};
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(keyAry, "-"));
        try {
            String luaScript = buildLuaScript();
            RedisScript<Number> redisScript = new DefaultRedisScript<Number>(luaScript, Number.class);
            Number count = redisUtil.excute(redisScript, keys, limitCount, limitPeriod);
            if (count != null && count.intValue() <= limitCount) {
                return pjp.proceed();
            } else {
                String classPath = method.getDeclaringClass().getName() + "." + method.getName();
                throw new RuntimeException("limitInterceptor - 限流被触发："
                        + "class:" + classPath
                        + ", keys:" + keys
                        + ", limitcount:" + limitCount
                        + ", limitPeriod:" + limitPeriod + "s");
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("limitInterceptor - 限流服务异常");
        }
    }
    /**
     * lua 脚本，为了保证执行 redis 命令的原子性
     */
    public String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
