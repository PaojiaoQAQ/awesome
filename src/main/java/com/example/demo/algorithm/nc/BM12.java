package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/12
 **/
public class BM12
{
    /**
     *
     * @param head ListNode类 the head node
     * @return ListNode类
     */
    public ListNode sortInList (ListNode head) {
        return dividListNode(head,0,0);
    }

    /**
     * 合并2个有序链表
     * @param list1
     * @param list2
     * @return
     */
    private ListNode mergeTwoList(ListNode list1, ListNode list2){
        ListNode cur = new ListNode(-1);
        ListNode result = cur;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1 == null){
            cur.next = list2;
        }else{
            cur.next = list1;
        }
        return result.next;
    }

    private ListNode dividListNode(ListNode list,int left, int right){
        return null;
    }
}
