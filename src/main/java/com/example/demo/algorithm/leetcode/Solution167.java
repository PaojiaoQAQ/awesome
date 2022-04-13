package com.example.demo.algorithm.leetcode;

/**
 * @Description: <br> 两数之和 II - 输入有序数组
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/7/20 17:08
*/
public class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0,right = numbers.length - 1;
        int[] index = new int[2];
        while(left < right){
            if(numbers[left] + numbers[right] == target){
                index[0] = left + 1;
                index[1] = right + 1;
                return index;
            }
            if((numbers[left] + numbers[right]) > target){
                right -- ;
            }else{
                left ++;
            }
        }
        return index;
    }
}
