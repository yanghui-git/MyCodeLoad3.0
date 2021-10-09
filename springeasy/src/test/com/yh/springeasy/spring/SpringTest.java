package com.yh.springeasy.spring;

import com.yh.springeasy.springscanner.MyClassPathDefinitonScanner;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 通过自定义的扫描器,扫描指定包下所有被@MyBean 注释的类
 * <p>
 * https://www.jianshu.com/p/d5ffdccc4f5d
 */
public class SpringTest {

    @Test
    public void testSimpleScan() {
        String BASE_PACKAGE = "com.yh.springeasy";
        GenericApplicationContext context = new GenericApplicationContext();
        MyClassPathDefinitonScanner myClassPathDefinitonScanner = new MyClassPathDefinitonScanner(context);
// 注册过滤器
        //   myClassPathDefinitonScanner.registerTypeFilter();
        int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
        context.refresh();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanCount);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
