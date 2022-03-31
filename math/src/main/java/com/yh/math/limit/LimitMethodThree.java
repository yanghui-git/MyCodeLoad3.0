package com.yh.math.limit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 漏桶算法
 *
 * 正常流程 以rate 恒定速率处理请求
 *
 * 但是不能应对突发流量，流量激增
 */
public class LimitMethodThree {

    //出水率 恒定速率出水
    static long rate = 1l;

    // 上一次请求刷新时间
    static long refreshTime = System.currentTimeMillis();

    static long currentWater = 0;

    // 桶最大容量
    static long capacity = 5l;

    /**
     * 漏桶算法
     *
     * @return
     */
    static synchronized boolean leakybucketLimitTryAcquire() {
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 出水量
        long outWater = (currentTime - refreshTime) / 1000 * rate;
        // 当前水剩余量
        currentWater = Math.max(0, currentWater - outWater);
        //当前水余量小于最大容量 桶未满 流量进入
        if (currentWater < capacity) {
            //容量+1
            currentWater++;
            return true;
        }
        // 限流
        return false;
    }

    public static void main(String[] args) throws Exception {
        // 模拟线程并发,同时20个线程启动,并发访问
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        List<Callable<String>> runnables = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            runnables.add(new Callable() {
                @Override
                public String call() throws Exception {
                    // 表示此段时间内,流量是稳定的
                    if (leakybucketLimitTryAcquire()) {
                        System.out.println("流量安全:" + Thread.currentThread());
                        System.out.println();
                    } else {
                        System.out.println("流量不安全:" + Thread.currentThread());
                    }
                    return "1";
                }
            });
        }
        executorService.invokeAll(runnables);
        System.in.close();
    }
}
