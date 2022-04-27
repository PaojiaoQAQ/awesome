package com.example.demo.common.controller;

import com.example.demo.common.entity.Result;
import com.example.demo.common.utils.IdempotenceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/27
 **/
@RestController
@RequestMapping("/api/idempotence")
public class IdempotenceController
{
    @Resource
    private IdempotenceUtil idempotenceUtil;
    @GetMapping("/generateId")
    public Result<String> generateId(){
        return new Result<>().success(idempotenceUtil.generateId());
    }
}
