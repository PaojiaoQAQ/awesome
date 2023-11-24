package com.example.demo.algorithm.nc;

/**
 * @description
 * @author tanyue
 * @Date 2022/5/12
 **/
public class BM10
{
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode fir = pHead1;
        ListNode sec = pHead2;
        while(fir != sec){
            fir = fir == null? pHead2: fir.next;
            sec = sec == null? pHead1: sec.next;
        }
        return fir;
    }
}
