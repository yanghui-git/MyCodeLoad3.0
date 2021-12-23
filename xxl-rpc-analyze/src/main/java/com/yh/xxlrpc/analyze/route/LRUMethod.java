package com.yh.xxlrpc.analyze.route;

import sun.plugin.javascript.navig.Link;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LRU 算法 淘汰最近经常未被使用
 * 维护最近经常使用的
 */
public class LRUMethod extends XxlRpcLoadMethod {

    @Override
    public String route() {
        return null;
    }


    static Queue<String> queue = new LinkedList();

    /**
     * LRU 队列实现
     * http://www.javashuo.com/article/p-fzwdxlfy-bh.html
     */
    static public void lru(String name) {
        //入队列
        //  判断此元素是否在队列中
        //  不存在时
        if (!queue.contains(name)) {
            //判断队列是否已满
            if (queue.size() >= 5) {
                // 移除头部元素
                System.out.println("移除头部元素： " + queue.poll());
            }
            // 插入数据到尾部
            queue.add(name);
        } else {
            // 存在此数据时
            // 将此数据 位置调整至 尾部
            // 先删除
            queue.remove(name);
            // 插入到尾部
            queue.add(name);
        }

    }

    public static void main(String[] args) {
        lru("1");
        lru("2");
        lru("3");
        lru("4");
        lru("5");
        System.out.println(queue);
        // 模拟新数据插入
        lru("6");
        System.out.println(queue);
        lru("7");
        System.out.println(queue);
        // 模拟旧数据访问
        lru("3");
        System.out.println(queue);
        // 模拟旧数据访问
        lru("5");
        System.out.println(queue);
        // 模拟旧数据访问
        lru("7");
        System.out.println(queue);
    }

   /* public static void main(String[] args) {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("3");
        queue.add("4");
        queue.add("2");
        queue.add("2");
        queue.remove("3");
        queue.removeAll(Collections.singleton("2"));
        System.out.println(queue);
    }*/
}
