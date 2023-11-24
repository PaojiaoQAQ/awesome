package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2021/8/7
 **/
public class NC78
{
    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode temp = null;
        while(head != null){
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}

