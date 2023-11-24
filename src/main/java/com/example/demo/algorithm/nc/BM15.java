package com.example.demo.algorithm.nc;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/13
 **/
public class BM15
{
    /**
     * 删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
     * 例如：
     * 给出的链表为1\to1\to21→1→2,返回1 \to 21→2.
     * 给出的链表为1\to1\to 2 \to 3 \to 31→1→2→3→3,返回1\to 2 \to 31→2→3.
     *
     * 数据范围：链表长度满足 0 \le n \le 1000≤n≤100，链表中任意节点的值满足 |val| \le 100∣val∣≤100
     * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates (ListNode head) {
        // write code here
        ListNode pre = new ListNode(-1001);
        pre.next = head;
        ListNode cur = new ListNode(-1);
        ListNode result = cur;
        int nextVal = 0;
        while(head != null){
            if(head.next == null){
                nextVal = -1001;
            }else{
                nextVal = head.next.val;
            }
            if(head.val != nextVal && head.val != pre.val){
                cur.next = head;
                cur = cur.next;
            }
            pre = head;
            head = head.next;
        }
        cur.next = null;
        return result.next;
    }
}
