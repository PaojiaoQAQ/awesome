package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/9
 **/
public class BM1
{
    /**
     *
     * @param head
     * @desc
     * 给定一个单链表的头结点pHead(该头节点是有值的 ， 比如在下图 ， 它的val是1)，长度为n，反转该链表后，返回新链表的表头。
     *
     * 数据范围： 0\leq n\leq10000≤n≤1000
     * 要求：空间复杂度 O(1)O(1) ，时间复杂度 O(n)O(n) 。
     *
     * 如当输入链表{1,2,3}时，
     * 经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
     * @return
     */
    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
