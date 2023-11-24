package com.example.demo.algorithm.nc;

import java.util.regex.Pattern;

/**
 * @description
 * @author tanyue
 * @Date 2022/6/29
 **/
public class RegularRule
{
    public static void main(String[] args)
    {
        String regex = "(?!\")";
        String reg = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,15}$";
        Pattern r = Pattern.compile(reg);
    }
}
