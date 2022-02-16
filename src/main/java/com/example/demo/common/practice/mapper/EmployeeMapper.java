package com.example.demo.common.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.practice.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tanyue
 *
 * @Date 2021/4/10
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>
{
}
