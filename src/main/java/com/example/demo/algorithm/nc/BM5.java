package com.example.demo.algorithm.nc;

import java.util.ArrayList;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/10
 **/
public class BM5
{
    /**
     * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
     *
     * 数据范围：节点总数满足 0 \le n \le 10^50≤n≤10
     * 5
     *  ，链表个数满足 1 \le k \le 10^5 \1≤k≤10
     * 5
     *    ，每个链表的长度满足 1 \le len \le 200 \1≤len≤200  ，每个节点的值满足 |val| <= 1000∣val∣<=1000
     * 要求：时间复杂度 O(nlogk)O(nlogk)
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        return divideMerge(lists,0,lists.size() - 1);
    }

    private ListNode mergeTowLists(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;
        while(list1.next != null && list2.next != null){
            if(list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1 != null){
            cur.next = list1;
        }else{
            cur.next = list2;
        }
        return dummyNode.next;
    }

    private ListNode divideMerge(ArrayList<ListNode> lists, int left, int right){
        if(left > right){
            return null;
        }
        if(left == right){
            return lists.get(left);
        }
        int mid = (left + right)/2;
        return mergeTowLists(divideMerge(lists,left,mid),divideMerge(lists,mid + 1, right));
    }
}
