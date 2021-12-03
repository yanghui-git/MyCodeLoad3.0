package com.yh.springeasy.other;

import org.junit.Test;

/**
 * String 源码探究
 * https://blog.csdn.net/csxypr/article/details/92378336
 */
public class StringTest {

    static String name = "test";

    /**
     * String
     * String类是不可变类，即一旦一个String对象被创建以后，包含在这个对象中的字符序列是不可改变的，直至这个对象被销毁。
     */
    @Test
    public void test() {
        String a = "123";
        a = "456";
        // 打印出来的a为456
        System.out.println(a);

    }

    /**
     * StringBuffer
     */
    @Test
    public void testTwo() {
        StringBuffer b = new StringBuffer("123");
        b.append("456");
        // b打印结果为：123456
        System.out.println(b);

    }

    /**
     * StringBuilder
     */
    @Test
    public void testThree() {
        StringBuilder b = new StringBuilder("123");
        b.append("456");
        // b打印结果为：123456
        System.out.println(b);

    }


    public static void main(String[] args) {
        System.out.println(name);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("12");
        System.out.println(stringBuffer);
    }

}
