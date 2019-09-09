package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        if(map.get('a') != null){
            map.put('a',map.get('a')+1);
        }else{
            map.put('a',0);
        }
        System.out.println(map.get('a'));
        Set<Map.Entry<Character,Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<Character,Integer>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<Character,Integer> entry = iterator.next();
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

    }


}
