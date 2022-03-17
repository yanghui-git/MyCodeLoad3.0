package com.yh.spring.circular.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMain {
    /**
     * 放置创建好的bean Map
     */
    private static Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

    public static void main(String[] args) throws Exception {
        // 假装扫描出来的对象
        Class[] classes = {ServiceA.class, ServiceB.class, ServiceC.class};
        // 假设项目开始实例化bean
        for (Class c : classes) {
            getBean(c);
        }
        // 验证
        System.out.println(getBean(ServiceA.class).getServiceB() == getBean(ServiceB.class));
        System.out.println(getBean(ServiceA.class) == getBean(ServiceC.class).getServiceA());
    }


    private static <T> T getBean(Class<T> beanClass) throws Exception {
        // 本文用类名小写 简单代替bean的命名规则
        String beanName = beanClass.getSimpleName().toLowerCase();
        // 如果已经是一个bean,则直接返回
        if (cacheMap.containsKey(beanName)) {
            return (T) cacheMap.get(beanName);
        }
        // 将对象本身实例化
        Object object = beanClass.getDeclaredConstructor().newInstance();
        // 放入缓存
        cacheMap.put(beanName, object);
        //把所有字段 当成需要注入的bean, 创建并注入到当前bean中
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // 获取需要注入字段的class
            Class fieldClass = field.getType();
            String filedBeanName = fieldClass.getSimpleName().toLowerCase();
            // 如果需要注入的bean,已经在缓存中,那么把缓存map的值 注入到此field即可
            // 如果缓存没有,则继续创建
            field.set(object,
                    cacheMap.containsKey(filedBeanName) ? cacheMap.get(filedBeanName) : getBean(fieldClass));
        }

        // 属性填充完成,返回
        return (T) object;
    }


}
