package com.yh.springeasy.initbean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模拟动态配置demo 热更新
 */
@Component
public class InitBeanTestDynamic implements InitializingBean {

    //开启一个线程 定时去改变age值
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public int getAgeDynamic() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                age += 10;
            //    System.out.println("模拟后台热更新改变 age=" + age);
            }
        }, 3l, 3l, TimeUnit.SECONDS);
        return age;
    }

    String name;

    int age;

    @Override
    public void afterPropertiesSet() throws Exception {
        name = "test";
        age = getAgeDynamic();
    }
}
