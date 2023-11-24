package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/12
 **/
public class BM8
{
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        //快慢指针
        if(pHead == null || k == 0){
            return null;
        }
        ListNode low = pHead;
        ListNode fast = pHead;
        while(fast != null && k-- > 0){
            fast = fast.next;
        }
        if(k > 0){
            return null;
        }
        while(fast != null){
            fast = fast.next;
            low = low.next;
        }
        return low;
    }
}
