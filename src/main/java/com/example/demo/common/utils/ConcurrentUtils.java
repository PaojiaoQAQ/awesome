package com.example.demo.common.utils;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description
 * @author tanyue
 * @Date 2022/4/20
 **/
@Slf4j
public class ConcurrentUtils
{
    private static int nThreads = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService executor = Executors.newFixedThreadPool(nThreads);
    /**
     * 使用指定线程池,为Runnable创建线程执行,并等待所有线程执行结束
     *
     * @param describe    任务描述
     * @param runnableLis 执行的线程
     */
    @SneakyThrows
    public static void syncParallelExec(String describe, List<Runnable> runnableLis) {

        Stopwatch sw = Stopwatch.createStarted();
        log.info("describe:{} 开始执行", describe);
        List<CompletableFuture<Void>> cfs = runnableLis.stream()
                .map(r -> CompletableFuture.runAsync(() -> r.run() ,executor))
                .collect(Collectors.toList());
        CompletableFuture<Void> allCf = CompletableFuture.allOf(cfs.toArray(new CompletableFuture[cfs.size()]));
        allCf.get();
        log.info("describe:{} 执行结束,用时:{}s", describe, sw.elapsed(TimeUnit.SECONDS));
    }
}
