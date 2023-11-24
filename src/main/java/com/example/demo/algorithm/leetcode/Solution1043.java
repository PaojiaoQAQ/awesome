package com.example.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1043 {
    public int lastStoneWeight(int[] stones) {
        List<Integer> stoneList = new ArrayList<>(stones.length);
        for(int i =0; i < stones.length; i++){
            stoneList.add(stones[i]);
        }
        Integer temp = 0;
        while (stoneList.size() > 2){
            Collections.sort(stoneList);
            temp = stoneList.get(stoneList.size()-1) - stoneList.get(stoneList.size()-2);
            stoneList = stoneList.subList(0,stoneList.size()-2);
            if(temp != 0){
                stoneList.add(temp);
            }
        }
        if(stoneList.size() == 2){
            Collections.sort(stoneList);
            return stoneList.get(stoneList.size()-1) - stoneList.get(stoneList.size()-2);
        }else{
            return stoneList.get(stoneList.size()-1);
        }
    }
}
