package com.example.demo.algorithm.leetcode;

import java.util.List;

/**
 * @Description: <br>三角形最小路径和(自底向上递推 递推公式f(i)(j) = min(f(i+1)(j), f(i+1)(j+1)) + triangle(i)(j))
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/7/14 17:01
*/
public class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for(int i = n - 1;i >= 0; i--){
            for(int j = 0; j< i + 1; j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
