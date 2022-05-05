package com.example.demo.practice.service.impl;

import com.example.demo.practice.service.DailyService;
import com.example.demo.common.utils.HttpClientUtils;
import com.example.demo.common.utils.ResourceUtil;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DailyserviceImpl implements DailyService
{
    private static int nThreads = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService executor = Executors.newFixedThreadPool(nThreads);
    @Override
    public String getWeatherInfo(String cityDm) {
        String uri = ResourceUtil.getAppconfigByKey("weather.uri") + cityDm;
        String respData = HttpClientUtils.doGet(uri,new HashMap<>());
        return respData;
    }
    @Override
    @SneakyThrows
    public void syncCal()
    {
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < 1000; i++){
            list.add(i);
        }
        List<List<Integer>> groupList = Lists.partition(list,100);
        List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();
        groupList.stream().forEach(
                item ->{
                    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->{
                        int sum = item.stream().mapToInt(Integer::intValue).sum();
                        System.out.println(sum);
                        return sum;
                    },executor);
                    completableFutureList.add(future);
                }
        );
        int sum =0;
        for(CompletableFuture<Integer> future: completableFutureList){
            sum = sum + future.get();
        }
        System.out.println(sum);

    }
}
