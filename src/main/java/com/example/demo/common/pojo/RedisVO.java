package com.example.demo.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisVO
{
    /**
     * key
     */
    private String key;
    /**
     * String value
     */
    private String value;
    /**
     * expire_time seconds
     */
    private Long expireTime;
}
