package com.yh.feign.produce.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * InvokeHandler
 */
public class DynamicStudentService implements InvocationHandler {

    /**
     * 要代理的真实对象
     */
    private Object student;

    public DynamicStudentService(Object student) {
        this.student = student;
    }

    /**
     * 动态代理上的所有方法应用
     *
     * @param proxy  代理类实例
     * @param method 被调用的方法对象
     * @param args   参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始操作");
        Object returnValue = method.invoke(student, args);
        System.out.println("结束操作");
        return returnValue;
    }
}
