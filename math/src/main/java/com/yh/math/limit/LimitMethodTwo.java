package com.yh.math.limit;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流算法
 * 滑动窗口
 * 10 秒内 最大访问数为5
 * 解决临界问题
 *
 * url:https://juejin.cn/post/6967742960540581918#heading-4
 *
 * 滑动窗口算法虽然解决了固定窗口的临界问题，但是一旦到达限流后，请求都会直接暴力被拒绝
 *
 * 也就是会丟是部分请求
 */
public class LimitMethodTwo {

    // 计数器,用于记录每个窗口的 技术，key为开始计数时间
    private static Map<Long, Integer> counters = new HashMap<>();

    //单位划分的小周期
    static private int SUB_CYCLE = 1;

    // 每10秒的阈值
    static private int threshold = 5;

    // 窗口为10秒
    static Long windowUnit = 10 * 1000l;

    synchronized static boolean slidingWindowsTryAcquire() {
        // 当前时间
        Long currentTime = System.currentTimeMillis() / 1000;
        //当前窗口总请求数
        int currentWindowNum = countCurrentWindow(currentTime);
        //超过阈值
        if (currentWindowNum > threshold) {
            return false;
        }
        // 计数器+1
        counters.put(currentTime, (Objects.isNull(counters.get(currentTime)) ? 0 : counters.get(currentTime)) + 1);
        return true;
    }

    /**
     * 统计当前窗口的请求数
     */
    static int countCurrentWindow(Long currentWindowTime) {
        //滑动计算窗口开始位置
        Long startTime = currentWindowTime - windowUnit;
        int count = 0;
        //遍历存储的计数器
        Iterator iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = (Map.Entry<Long, Integer>) iterator.next();
            // 删除无效过期的子窗口计数器
            if (entry.getKey() < startTime) {
                iterator.remove();
            } else {
                //累加当前窗口的所有计数器之和
                count = count + entry.getValue();
            }
        }
        return count;
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
                    if (slidingWindowsTryAcquire()) {
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
