package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.User;
import com.example.demo.rabbitmq.Producer;
import com.example.demo.service.DailyService;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtils;
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
    private DailyService dailyService;
    @Autowired
    private Producer producer;

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
        String key = "user1";
        String value = JSON.toJSONString(user);
        RedisUtils redisUtils = new RedisUtils();
        String flag = redisUtils.set(key, value,0);
        return redisUtils.get(key,0);

//        redisTemplate.opsForValue().set("redisUser",user);
    }

    @GetMapping(value = "getRedisTest")
    public Object getPojo(){
        return null;
//        return redisTemplate.opsForValue().get("redisUser");
    }
    @RequestMapping(value = "")
    public String helloDocker(){
        return "Hello Docker!";
    }

    @RequestMapping(value = "weather")
    public String getLocalWeather(){
        return dailyService.getWeatherInfo("101010100");
    }

    @RequestMapping(value = "mqtest")
    public void mqTest(){
        producer.send();
    }

}
