package com.example.demo;

import com.example.demo.utils.ReadFileUtils;
import com.example.demo.utils.ResourceUtil;
import org.junit.Test;

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
        String path = "e:/jsonFile.json";
        String s = ReadFileUtils.readFileStr(path);
        System.out.println(s);
    }
    @Test
    public void resourceTest(){
        String url = ResourceUtil.getAppconfigByKey("language");
        System.out.println(url);
    }
}
