package com.example.demo.algorithm.nc;

/**
 * @description BM7 链表中环的入口结点
 * @author tanyue
 * @Date 2022/5/12
 **/
public class BM7
{
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode fast = pHead;
        ListNode low = pHead;
        ListNode meetNode = getMeetNode(low,fast);
        if(meetNode == null){
            return null;
        }
        //设起点到入口距离为a，入口到相遇点为b，环的长度为c，有 a + b = (kc + b) + a/ 2 -> a = (k -1 )c + c - b
        // 此时两个指针一个从相遇点触发，一个从起点出发 最终会交于入口
        fast = pHead;
        low = meetNode;
        while(fast != low){
            fast = fast.next;
            low = low.next;
        }
        return low;
    }

    /**
     * 根据快慢指针获取相遇节点
     * @param low
     * @param fast
     * @return
     */
    private ListNode getMeetNode(ListNode low,ListNode fast){
        while(fast != null && fast.next != null){
            low = low.next;
            fast = fast.next.next;
            if(low.equals(fast)){
                return low;
            }
        }
        return null;
    }
}
