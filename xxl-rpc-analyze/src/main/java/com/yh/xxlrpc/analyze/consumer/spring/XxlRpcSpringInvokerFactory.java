package com.yh.xxlrpc.analyze.consumer.spring;

import com.yh.xxlrpc.analyze.consumer.controller.ConsumerTestController;
import com.yh.xxlrpc.analyze.consumer.controller.XxlRpcReference;
import com.yh.xxlrpc.analyze.consumer.controller.XxlRpcReferenceBean;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;

public class XxlRpcSpringInvokerFactory extends InstantiationAwareBeanPostProcessorAdapter implements InitializingBean {

    /**
     * Bean 初始化之后调用
     */
    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        // System.out.println("进入自定义Bean 初始化之后方法,拦截bean" + bean + "   beanName" + beanName);
        // 对bean中的field进行骚操作 / 对里面的rpc需要用到的bean进行操作
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {

            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                //针对性帅选bean/ 带有XxlRpcReference 是说明需要进行 rpc调用的/ 后续会生成动态代理类
                if (!field.isAnnotationPresent(XxlRpcReference.class)) {
                    return;
                }
                Object str1 = bean;
                String str2 = beanName;
                System.out.println("进入自定义Bean 初始化之后方法,拦截bean" + str1 + "   beanName" + str2);
                System.out.println("准备对 远程调用的 field 动手" + field);
                // 相当于将 需要rpc 调用的service bean 给动态代理掉
                Object serviceProxy = null;
                try {
                    serviceProxy = XxlRpcReferenceBean.getObject(field.getType());
                } catch (Exception e) {
                    System.out.println(e);
                }

                field.setAccessible(true);
                // 重新设置bean 相当于这个field bean 重新构造为 底层远程执行的bean
                field.set(bean, serviceProxy); //设置为动态代理
            }
        });
        return super.postProcessAfterInitialization(bean, beanName);
    }

    /**
     * other isAnnotationPresent 方法
     */
    @Test
    public void test() {
        System.out.println(new ConsumerTestController().getClass().isAnnotationPresent(XxlRpcReference.class));
        System.out.println(new ConsumerTestController().getClass().isAnnotationPresent(RequestMapping.class));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //开启后台定时线程 去 服务端轮询获取 服务提供者地址
        new XxlRpcInvokerFactory().start();
    }
}
