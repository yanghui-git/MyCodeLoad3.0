package com.yh.springeasy.initbean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Bean init test one InitializingBean
 */
@Component
public class InitBeanTestOne implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initial bean test one ......");
    }

}
