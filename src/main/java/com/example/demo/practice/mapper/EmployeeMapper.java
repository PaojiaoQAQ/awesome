package com.example.demo.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.practice.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author tanyue
 *
 * @Date 2021/4/10
 **/
@Mapper
@Component
public interface EmployeeMapper extends BaseMapper<Employee>
{
}
