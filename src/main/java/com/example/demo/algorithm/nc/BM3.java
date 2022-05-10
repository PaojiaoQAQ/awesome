package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/10
 **/
public class BM3
{
    /**
     *将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
     * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
     * 你不能更改节点中的值，只能更改节点本身。
     *
     * 数据范围： \ 0 \le n \le 2000 0≤n≤2000 ， 1 \le k \le 20001≤k≤2000 ，链表中每个元素都满足 0 \le val \le 10000≤val≤1000
     * 要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     * 例如：
     * 给定的链表是 1\to2\to3\to4\to51→2→3→4→5
     * 对于 k = 2k=2 , 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
     * 对于 k = 3k=3 , 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode tail = head;
        //遍历k个节点，不足k个不用翻转直接返回
        for(int i = 0; i < k; i++){
            if(tail == null){
                return head;
            }
            tail = tail.next;
        }
        ListNode pre = null;
        ListNode cur = head;
        //同BM1翻转链表
        while(cur != tail){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = reverseKGroup(tail,k);
        return pre;
    }
}
