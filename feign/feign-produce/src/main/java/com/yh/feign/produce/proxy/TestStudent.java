package com.yh.feign.produce.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 测试
 * 静态代理 https://www.zhihu.com/column/c_144466663
 */
public class TestStudent {

    public static void main(String[] args) {
        //配置系统属性为true,代理类生成时将自动写入磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //代理的真实对象
        StudentService studentService = new StudentServiceImpl();
        InvocationHandler invocationHandler = new DynamicStudentService(studentService);
        ClassLoader classLoader = studentService.getClass().getClassLoader();
        Class[] interfaces = studentService.getClass().getInterfaces();

        /**
         * 生成代理对象
         * 核心参数
         * 1 定义代理类的类加载器
         * 2 代理类要实现的接口列表
         * 3 将方法调用分派到具有指定调用处理程序的代理实例的调用处理程序
         *               由指定的类加载器定义的代理类
         *                并实现指定的接口
         *
         */
        StudentService service = (StudentService) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        service.add();
    }
}
