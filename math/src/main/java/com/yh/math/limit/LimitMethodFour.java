package com.yh.math.limit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 令牌桶算法
 * <p>
 * 一定的速率向桶中放令牌
 * 拿令牌
 * 拿到令牌流量通过
 * <p>
 * 如果令牌发放的策略正确，这个系统即不会被拖垮，也能提高机器的利用,即提升rate
 *
 * 限制的是平均流入速率。令牌桶算法在突发情况下，处理速率可以超过限制。算是漏桶的改进版，能够防止一定程度的流量突发。突发流量大于生成速率但是小于桶的容量，也是能够接受的
 */
public class LimitMethodFour {

    // 令牌的产生速率
    static long rate = 10l;

    // 刷新时间
    static long refreshTime = System.currentTimeMillis();

    //当前剩余令牌数
    static long currentCount = 0;

    // 令牌桶最大容量
    static long capacity = 10;

    synchronized static boolean tokenBucketTryAcquire() {
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 这段时间产生的令牌
        long generationCount = ((currentTime - refreshTime) / 1000) * rate;
        // 当前剩余令牌
        currentCount = Math.min(capacity, generationCount + currentCount);
        // 更新时间
        refreshTime = currentTime;
        // 如果当前令牌大于0 申请到令牌,流量通过
        if (currentCount > 0) {
            // 令牌数-1
            currentCount--;
            return true;
        }
        // 没有令牌
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
                    Thread.sleep(1000l);
                    // 表示此段时间内,流量是稳定的
                    if (tokenBucketTryAcquire()) {
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
