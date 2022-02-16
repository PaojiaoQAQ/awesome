package com.example.demo.common.practice.mapper;

import com.example.demo.common.practice.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUserById(@Param("id") String userId);
}
