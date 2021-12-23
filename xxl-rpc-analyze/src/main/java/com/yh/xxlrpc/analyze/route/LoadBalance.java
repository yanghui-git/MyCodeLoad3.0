package com.yh.xxlrpc.analyze.route;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 负载均衡算法 /平均分配
 * 消费顺讯 1--2--3--1--2--3--1--2--3
 */
public class LoadBalance extends XxlRpcLoadMethod {

    ConcurrentMap<String, Integer> routeCountEachJob = new ConcurrentHashMap<String, Integer>() {
        {
            put("demoService", 0);
        }
    };


    @Override
    public String route() {
        // 获取方法当前执行次数
        int count = routeCountEachJob.get("demoService");
        //选择机器
        String address = super.produceUrl[count % super.produceUrl.length];
        //更新保存方法执行次数
        count = count + 1;
        routeCountEachJob.put("demoService", count);
        return address;
    }

    public static void main(String[] args) throws InterruptedException {
        LoadBalance loadBalance = new LoadBalance();
        while (true) {
            Thread.sleep(2000l);
            System.out.println("当前任务正在被机器: " + loadBalance.route() + " 执行");
        }
    }
}
