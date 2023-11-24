package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.practice.dto.EmployeeDTO;
import com.example.demo.common.practice.pojo.Employee;
import com.example.demo.common.utils.ReadFileUtils;
import com.example.demo.common.utils.ResourceUtil;
import com.example.demo.common.utils.ThreadPoolUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class dailyTest {
    @Test
    public void test1(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        StringBuilder sb = new StringBuilder();
        sb.toString();
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
    }
    @Test
    public void readfileTest(){
        String path = "f:/jsonFile.json";
        String s = ReadFileUtils.readFileStr(path);
        System.out.println(s);
    }
    @Test
    public void resourceTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(sdf.format(new Date()));
        String url = ResourceUtil.getAppconfigByKey("language");
        System.out.println(url);
    }
    @Test
    public void threadPoolTest(){
        ThreadPoolUtils thread  = ThreadPoolUtils.getInstance();
        for(int i = 0; i< 200; i++){
            thread.excute(()->
                System.out.println(Thread.currentThread().getName() + " is running")
            );
        }
    }
    @Test
    public void saveEmployee(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = Employee.builder()
                .Id(UUID.randomUUID().toString())
                .name("谈越")
                .code("000001")
                .password("ty123456")
                .deptId("000001")
                .build();
        employeeDTO.setEmployee(employee);
        System.out.println(JSON.toJSONString(employeeDTO));
    }
}
