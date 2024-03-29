package com.example.demo.practice.service.impl;

import com.example.demo.practice.service.UserService;
import com.example.demo.practice.mapper.UserMapper;
import com.example.demo.practice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }
}
