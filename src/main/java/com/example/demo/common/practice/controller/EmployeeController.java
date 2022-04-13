package com.example.demo.common.practice.controller;

import com.example.demo.common.entity.Result;
import com.example.demo.common.practice.dto.EmployeeDTO;
import com.example.demo.common.practice.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author tanyue
 * @Date 2021/4/6
 **/
@RestController
@RequestMapping(value = "app/employee/v1")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;
    @PutMapping("/saveEmployee")
    public Result saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.saveEmployee(employeeDTO.getEmployee());
        return new Result().success();
    }
}