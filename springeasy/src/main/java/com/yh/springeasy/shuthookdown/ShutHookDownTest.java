package com.yh.springeasy.shuthookdown;

import lombok.SneakyThrows;
import org.omg.SendingContext.RunTime;

import java.io.IOException;

public class ShutHookDownTest {

   /* public static void main(String[] args) throws IOException {
        System.out.println("我是主线程，说点什么好呢？");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("优雅下线 demo");
            }
        });

        System.in.read();
        System.exit(0);
    }*/

    public static void main(String[] args) {
        System.out.println("我是主线程，说点什么好呢？");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("优雅下线 demo");
            }
        });

        // 接受来自其他方式的退出信号
        new Thread() {
            @Override
            public void run() {
                try {
                    System.in.read();
                } catch (Exception e) {

                }
                System.exit(0);
            }
        }.start();
    }
}
