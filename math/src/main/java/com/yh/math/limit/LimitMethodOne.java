package com.yh.math.limit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流算法
 * 固定窗口时间算法
 * <p>
 * 限制 10秒内 最大请求数为3个
 *
 * 问题: 临界问题
 *
 * 假设
 *  00:00--00:10 秒限制为5个请求
 *  那么
 *  00:08--00:10 5req
 *  00:10--00:15 5req
 *  -->
 *  00:08--00:15 10 req error
 */
public class LimitMethodOne {

    // 上一次请求时间，即左窗口
    static long lastReqTime = System.currentTimeMillis();

    // 计数器 记录当前请求了多少流量,流量通过则+1
    static int counter = 0;

    // 阈值
    static int threshold = 3;

    // 窗口为10秒
    static long windowUnit = 10 * 1000l;

    static synchronized boolean fixedWindowsTryAcquire() {
        // 当前请求时间,即右窗口
        long currentTime = System.currentTimeMillis();
        // 判断是否处于限制时间窗口内
        // 超出了时间窗口，则进入下一个窗口,计数器归0
        if (currentTime - lastReqTime > windowUnit) {
            //计数器归零
            counter = 0;
            // 下一个窗口 左值
            lastReqTime = currentTime;
        }
        //判断此请求 窗口内，即10s内，是否超出了阈值,未超出,表示可以访问
        if (counter < threshold) {
            //计数器+1
            counter++;
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        // 模拟线程并发,同时20个线程启动,并发访问
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        List<Callable<String>> runnables = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            runnables.add(new Callable() {
                @Override
                public String call() throws Exception {
                    // 表示此段时间内,流量是稳定的
                    if (fixedWindowsTryAcquire()) {
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
