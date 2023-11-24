package com.example.demo.algorithm.leetcode;

/**
 * @Description: leetcode 42 接雨水问题 双指针
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/6/19 10:09
*/
public class Solution42 {
    public int trap(int[] height) {
        int left_max = 0,right_max = 0,left = 0,right = height.length-1,ans = 0;
        while(left < right){
            if(height[left] < height[right]){
                if(height[left] > left_max){
                    left_max = height[left];
                }else{
                    ans += left_max - height[left];
                }
                left ++;
            }else{
                if(height[right] > right_max){
                    right_max = height[right];
                }else{
                    ans += right_max - height[right];
                }
                right --;
            }
        }
        return ans;
    }
}
