package com.example.demo.practice.service.impl;

import com.example.demo.practice.mapper.EmployeeMapper;
import com.example.demo.practice.pojo.Employee;
import com.example.demo.practice.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author tanyue
 * @Date 2021/4/6
 **/
@Service
public class EmployeeService implements IEmployeeService
{
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void saveEmployee(Employee employee)
    {
        employeeMapper.insert(employee);
    }
}
