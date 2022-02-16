package com.example.demo.common.practice.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.common.practice.pojo.Employee;

/**
 * @description
 * @author tanyue
 * @Date 2021/4/6
 **/
public class EmployeeDTO
{
    @JSONField(name = "employee")
    Employee employee;

    public Employee getEmployee()
    {
        return employee;
    }
    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
}
