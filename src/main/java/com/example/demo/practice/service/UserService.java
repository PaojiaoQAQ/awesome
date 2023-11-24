package com.example.demo.practice.service;

import com.example.demo.practice.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(String id);
}
