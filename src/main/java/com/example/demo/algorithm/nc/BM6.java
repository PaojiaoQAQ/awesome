package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/10
 **/
public class BM6
{
    public boolean hasCycle(ListNode head) {
        //快慢指针
        ListNode fast = head;
        ListNode low = head;
        while(fast != null && fast.next != null){
            low = low.next;
            fast = fast.next.next;
            if(low == fast){
                return true;
            }
        }
        return false;
    }
}
