package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2021/8/7
 **/
public class NC140
{
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort (int[] arr) {
        // write code here
        return null;
    }
    void quickSort(int[] arr, int left, int right){
        if(left > right){
            return;
        }
        //确定游标，游标左边都比游标小右边都比游标大
        int pivot = partition(arr,left,right);
        quickSort(arr , left, pivot -1);
        quickSort(arr,pivot + 1, right);
    }
    int partition(int[] arr, int left, int right){
        int pivot = left;
        while(left < right){
            if(arr[left] > arr[pivot]){
                swap(arr[left], arr[pivot]);
                pivot ++;
            }
            left ++ ;
        }
        return 0;
    }
    void swap(int a, int b){
        int temp = a ;
        a = b;
        b = temp;
    }
}
