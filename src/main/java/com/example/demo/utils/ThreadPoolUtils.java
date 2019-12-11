package com.example.demo.utils;

import java.util.concurrent.*;

public class ThreadPoolUtils {
    /**
     * 根据cpu的数量动态的配置核心线程数和最大线程数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数 = CPU核心数 + 1
     */
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    /**
     * 线程池最大线程数 = CPU核心数 * 2 + 1
     */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    /**
     * 非核心线程闲置时超时1s
     */
    private static final int KEEP_ALIVE = 1;
    /**
     *  线程池的对象
     */
    private ThreadPoolExecutor executor;
    /**
     * 要确保该类只有一个实例对象，避免产生过多对象消费资源，所以采用单例模式
     */
    private ThreadPoolUtils() {
    }

    private static volatile ThreadPoolUtils instance;

    public static ThreadPoolUtils getInstance(){
        if(instance == null){
            synchronized (ThreadPoolUtils.class){
                if(instance == null){
                    instance = new ThreadPoolUtils();
                }
            }
        }
        return instance;
    }
    /**
     * 开启一个无返回结果的线程
     * @param runnable
     */
    public void excute(Runnable runnable){
        if (executor == null){
            /**
             * corePoolSize:核心线程数
             * maximumPoolSize：线程池所容纳最大线程数(workQueue队列满了之后才开启)
             * keepAliveTime：非核心线程闲置时间超时时长
             * unit：keepAliveTime的单位
             * workQueue：等待队列，存储还未执行的任务
             * threadFactory：线程创建的工厂
             * handler：异常处理机制
             *
             */
            executor = new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE, TimeUnit.SECONDS,new ArrayBlockingQueue<>(20),
                    Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        }
        executor.execute(runnable);
    }

    /**
     * 开启一个有返回结果的线程
     * @param callable
     * @return
     */
    public Future submit(Callable callable){
        if(executor == null){
        /**
         * corePoolSize:核心线程数
         * maximumPoolSize：线程池所容纳最大线程数(workQueue队列满了之后才开启)
         * keepAliveTime：非核心线程闲置时间超时时长
         * unit：keepAliveTime的单位
         * workQueue：等待队列，存储还未执行的任务
         * threadFactory：线程创建的工厂
         * handler：异常处理机制
         *
         */
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        }
        return executor.submit(callable);
    }

    /**
     * 把任务移除等待队列
     * @param runnable
     */
    public void cancel(Runnable runnable) {
        if (runnable != null) {
            executor.getQueue().remove(runnable);
        }
    }


}
