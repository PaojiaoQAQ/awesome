package com.example.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
       Map<Integer,Integer> map = new HashMap<>();
        for(int p: nums){
            if(map.get(p) != null){
                map.put(p,map.get(p) + 1);
            }else{
                map.put(p,1);
            }
        }
        List<Map.Entry<Integer,Integer>> list= new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        int[] result = new int[k];
        int i = 0;
        for(Map.Entry entry:list){
            if(i >= k){
                break;
            }
            result[i] = (Integer)entry.getKey();
            i ++;
        }
        return result;
    }
}
