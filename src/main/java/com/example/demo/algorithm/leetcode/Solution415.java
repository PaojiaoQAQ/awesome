package com.example.demo.algorithm.leetcode;

/**
 * @Description: 字符串相加
 *

 * @param
 * @return:
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/8/3 15:24
*/
public class Solution415 {
    public String addStrings(String num1, String num2) {
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();
        int i = char1.length -1;
        int j = char2.length -1;
        //进位标志
        int carry = 0;
        int n1,n2,temp= 0;
        StringBuffer result = new StringBuffer();
        while(i >= 0 || j >= 0){
            n1 = i < 0?0:char1[i] - '0';
            n2 = j < 0?0:char2[j] - '0';
            temp = n1 + n2 + carry;
            if(temp > 9){
                temp = temp%10;
                carry = 1;
            }else{
                carry = 0;
            }
            result.append(temp);
            i--;
            j--;
        }
        if(carry == 1){
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
