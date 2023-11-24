package com.example.demo.common.exception;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
public class IdempotencyException extends RuntimeException
{
    public IdempotencyException(String message){
        super(message);
    }
}
