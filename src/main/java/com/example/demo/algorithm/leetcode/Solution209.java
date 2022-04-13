package com.example.demo.algorithm.leetcode;

/**
 *  * @Description: 长度最小的子数组问题(滑动窗口)
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/6/28 9:29
*/
public class Solution209 {
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE,sum = 0,right = 0,left = 0;
        while (right < nums.length){
            sum += nums[right++];
            while(sum >= s){
                min = Math.min(min,right - left);
                sum -= nums[left++];
            }
        }
        return min == Integer.MAX_VALUE?0: min;
    }
}
