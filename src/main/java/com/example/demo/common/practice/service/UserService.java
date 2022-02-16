package com.example.demo.common.practice.service;

import com.example.demo.common.practice.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(String id);
}
