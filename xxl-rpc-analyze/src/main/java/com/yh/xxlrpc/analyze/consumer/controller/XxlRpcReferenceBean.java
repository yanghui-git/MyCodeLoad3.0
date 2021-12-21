package com.yh.xxlrpc.analyze.consumer.controller;

import com.yh.xxlrpc.analyze.consumer.demo.DemoServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理执行目标方法的动态bean  / 模拟远程执行
 */
public class XxlRpcReferenceBean {

    /**
     * @param interfaceTest 代理类要实现的接口列表
     */
    public static Object getObject(Class<?> interfaceTest) {

        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{interfaceTest},
                // 匿名函数 动态代理了
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 模拟远程方法执行
                        // 这里直接本地调了
                        method.invoke(new DemoServiceImpl(),args);

                        return "远程方法执行成功";
                    }
                });
    }

}
