package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/12
 **/
public class BM11
{
    /**
     *
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
        // write code here
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        ListNode cur = new ListNode(-1);
        ListNode pre = cur;
        int inc = 0;
        while(head1 != null || head2!= null){
            int x = head1 == null? 0: head1.val;
            int y = head2 == null? 0: head2.val;
            ListNode node = new ListNode(0);
            if( x + y + inc > 9){
                node.val = x + y + inc - 10;
                inc = 1;
            }else{
                node.val = x + y + inc;
                inc = 0;
            }
            cur.next = node;
            cur = cur.next;
            if(head1 != null){
                head1 = head1.next;
            }
            if(head2 != null){
                head2 = head2.next;
            }
        }
        if(inc > 0){
            ListNode incNode = new ListNode(1);
            cur.next = incNode;
        }
        ListNode result = pre.next;
        result = reverseList(result);
        return result;
    }

    private ListNode reverseList(ListNode head){
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
