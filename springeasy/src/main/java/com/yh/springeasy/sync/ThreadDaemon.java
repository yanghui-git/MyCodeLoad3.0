package com.yh.springeasy.sync;

/**
 * 守护线程 https://blog.csdn.net/huangzhilin2015/article/details/115055397
 */
public class ThreadDaemon {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000L);
                        System.out.println("still running.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //设置线程为用户线程/ 默认也是用户线程 / 主程序结束此线程不会结束
        thread.setDaemon(false);
        thread.start();
        Thread.sleep(3000L);
        System.out.println("主线程退出");
    }

/*

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000L);
                        System.out.println("still running.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //设置线程为守护线程 / 主程序结束 线程结束
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(3000L);
        System.out.println("主线程退出");
    }

*/

}
