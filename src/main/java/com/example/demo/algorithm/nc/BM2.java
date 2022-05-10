package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/7
 **/
public class BM2
{
    /**
     *
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     * @desc
     * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
     * 例如：
     * 给出的链表为 1\to 2 \to 3 \to 4 \to 5 \to NULL1→2→3→4→5→NULL, m=2,n=4m=2,n=4,
     * 返回 1\to 4\to 3\to 2\to 5\to NULL1→4→3→2→5→NULL.
     *
     * 数据范围： 链表长度 0 < size \le 10000<size≤1000，0 < m \le n \le size0<m≤n≤size，链表中每个节点的值满足 |val| \le 1000∣val∣≤1000
     * 要求：时间复杂度 O(n)O(n) ，空间复杂度 O(n)O(n)
     * 进阶：时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)
     */
    public ListNode reverseBetween(ListNode head, int m, int n)
    {
        // write code here
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        //左边界前一个
        ListNode pre = dummyNode;
        for(int i = 0; i < m - 1; i++)
        {
            pre = pre.next;
        }
        //右边界
        ListNode right = pre;
        for(int i =0;i< n-m+1;i++){
            right = right.next;
        }
        //左边界
        ListNode left = pre.next;
        ListNode cur = right.next;
        //切断右边界
        right.next = null;
        reverseList(left);
        //拼接链表
        pre.next = right;
        left.next = cur;
        return dummyNode.next;
    }

    private void reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
    }
}
