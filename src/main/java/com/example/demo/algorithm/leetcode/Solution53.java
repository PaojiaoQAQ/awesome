package com.example.demo.algorithm.leetcode;

public class Solution53 {
    public int maxSubArray(int[] nums) {
        int prex = 0,result = nums[0];
        for(int i=0 ; i< nums.length; i++){
            prex =  Math.max(prex + nums[i], nums[i]);
            result = Math.max(result,prex);
        }
        return result;
    }
}
