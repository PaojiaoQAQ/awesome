package com.example.demo.practice.controller;

import com.example.demo.practice.pojo.RequestEntity;
import com.example.demo.practice.pojo.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BlogController {
    @PostMapping(value = "login")
    public ResponseEntity login(RequestEntity requestEntity){
        ResponseEntity responseEntity = new ResponseEntity();
        System.out.println(1);
        responseEntity.setData("ok");
        responseEntity.setStatus("success");
        return responseEntity;
    }

}
