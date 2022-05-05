package com.example.demo.practice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author tanyue
 *
 * @Date 2021/4/6
 **/
@Data
@Builder
@TableName("sys_employee")
public class Employee
{
    @TableField("id")
    private String Id;
    @TableField("name")
    private String name;
    @TableField("dept_id")
    private String deptId;
    @TableField("password")
    private String password;
    @TableField("code")
    private String code;
}
