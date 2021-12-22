package com.yh.xxlrpc.analyze.consumer.spring;


import java.util.Map;
import java.util.concurrent.*;

/**
 * stub 用于消费者端 后台线程定时轮询注册中心 去获取生产者地址
 * 维护本地生产者地址
 * stub 访问目标url https://www.jianshu.com/p/9ccdea882688 stub理解
 */
public class XxlRpcInvokerFactory {
    Map<String, String> urlMap = new ConcurrentHashMap<>();

    public void start() throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始定时轮询注册中心 获取生产者地址");
            }
        }, 3000, 10000, TimeUnit.MILLISECONDS);
    }
}
