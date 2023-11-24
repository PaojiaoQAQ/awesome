package com.example.demo.algorithm.leetcode;

/**
 * @Description: 718. 最长重复子数组(动态规划)
 *
 * @version: 1.0.0
 * @Author: tanyue
 * @Date: 2020/7/1 15:01
*/
public class Solution718 {
    public int findLength(int[] A, int[] B) {
        if(A.length == 0 || B.length == 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][B.length + 1];
        int result = 0;
        for(int i = 1; i< A.length + 1; i++){
            for(int j = 1; j< B.length + 1; j++){
                if(A[i - 1] == B[j - 1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(dp[i][j],result);
                }
            }
        }
        return result;
    }
}
