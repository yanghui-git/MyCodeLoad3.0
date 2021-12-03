package com.yh.springeasy.invoke;

/**
 * https://blog.csdn.net/aitangyong/article/details/54137067
 * JDK8新特性：函数式接口@FunctionalInterface的使用说明
 */
@FunctionalInterface
interface Func {
    public boolean func(String str);
}
