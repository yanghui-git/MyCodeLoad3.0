package com.yh.math.skiptable;

import org.junit.Test;

/**
 * 测试跳表的基本用法
 */
public class TestSkipListMap {

    @Test
    public void test() {
        SkipListMap<String, Integer> skipListMap = new SkipListMap<>();
        //1.1 插入
        skipListMap.put("A", 1);
        skipListMap.put("B", 2);
        skipListMap.put("J", 10);
        skipListMap.put("D", 4);
        skipListMap.put("C", 3);
        skipListMap.put("E", 5);
        skipListMap.put("I", 9);
        skipListMap.put("F", 6);
        skipListMap.put("H", 8);
        skipListMap.put("G", 7);

        //1.2 遍历
        skipListMap.forEach();
        System.out.println("--------------------------------------------------");

        //2.1 查找
        System.out.println("KEY=D，VALUE=" + skipListMap.get("D"));
        System.out.println("KEY=H，VALUE=" + skipListMap.get("H"));
        //2.2 遍历
        skipListMap.forEach();
        System.out.println("--------------------------------------------------");

        //3. 删除
        skipListMap.remove("B");
        skipListMap.remove("C");
        skipListMap.remove("G");
        //3.2 遍历
        skipListMap.forEach();
        System.out.println("--------------------------------------------------");
    }

}