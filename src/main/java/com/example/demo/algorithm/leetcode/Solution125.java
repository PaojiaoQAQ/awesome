package com.example.demo.algorithm.leetcode;

import java.util.Stack;

/**
 * @Description: 验证回文串
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/6/19 10:38
*/
public class Solution125 {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        StringBuilder reverseSb = new StringBuilder();
        Stack st = new Stack();
        for(Character c: s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
                st.push(c);
            }
        }
        while(!st.isEmpty()){
            reverseSb.append(st.pop());
        }
        return sb.toString().equals(reverseSb.toString());
    }
}

