package com.example.demo.algorithm.leetcode;

/**
 * @Description: 买卖股票的最佳时机
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/6/28 14:53
*/
public class Solution121 {
    public int maxProfit(int[] prices) {
        int left = 0,right = 0,value = 0;
        while(++right < prices.length ){
            if(prices[right] > prices[left]){
                value = Math.max(value,prices[right] - prices[left]);
            }else{
                left = right;
            }
        }
        return value;
    }
}
