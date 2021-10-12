package com.yh.springeasy.initbean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Bean init test three 判断启动顺序
 * 先调用InitializingBean 方法  再调用 xml.initMethod
 *
 * https://www.cnblogs.com/weiqihome/p/8922937.html
 *
 * AbstractAutowireCapableBeanFactory.invokeInitMethods
 */
@Component
public class InitBeanTestThree implements InitializingBean {

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initial bean test three.1  ......");
    }


    public void testInit() {
        System.out.println("Initial bean test three.2  .....");
    }

}
