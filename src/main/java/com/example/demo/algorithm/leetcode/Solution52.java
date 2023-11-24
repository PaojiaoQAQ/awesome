package com.example.demo.algorithm.leetcode;

public class Solution52 {
    private static int count = 0;
    public  int totalNQueens(int n) {
        count = 0;
        //存放皇后在棋盘的坐标，数组下标为行，数组值为列
        int queen[] = new int[n];
        //计数器
        placeQueen(0,queen,n);
        return count;
    }
    //向下一行放棋子
    public  void placeQueen(int row, int queen[],int n){
        for(int j = 0; j < n; j++){
            if(row == n){
                count ++;
                break;
            }
            queen[row] = j;
            if(row == 0 || valid(queen,row,j)){
                placeQueen(row + 1,queen,n);
            }
        }

    }
    //检测所放棋子是否合适
    public  boolean valid(int queen[],int row ,int col){
        for(int k =0;k < row; k++){
            if(col == queen[k] || Math.abs(queen[k] - col) == Math.abs(row - k)){
                return false;
            }
        }
        return true;
    }
}
