package com.yh.springeasy.sync;

import com.yh.springeasy.util.HttpUtil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncTest {

    private static int result = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(400);
        Set<Callable<String>> threads = new HashSet<>();
        for (int i = 0; i < 300; i++) {
            threads.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                  //  Thread.sleep(500l);
                    HttpUtil.get("http://localhost:8081/sync");
                    // 普通 类并发
                    //  System.out.println("result....." + result++);
                    return LocalDateTime.now().toString();
                }
            });
        }
        executorService.invokeAll(threads);
        System.in.read();
    }
}
