package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Controller
public class ProduceController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping(value = "/hello/{name}")
    public String helloTest(@PathVariable("name") String name){
        return "hello world! " + name;
    }

    @GetMapping(value = "/getAllUserInfo/{id}")
    public User getAllUserInfo(@PathVariable("id") String id){
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping(value = "/saveRedisTest")
    public String savePojo(){
        User user = new User();
        user.setName("redis测试");
        user.setPassword("3333");
        user.setUserid("111111");
        redisTemplate.opsForValue().set("redisUser",user);
        return "success";

    }

    @GetMapping(value = "getRedisTest")
    public Object getPojo(){
        return redisTemplate.opsForValue().get("redisUser");
    }
    @RequestMapping(value = "")
    public String helloDocker(){
        return "Hello Docker!";
    }
}
