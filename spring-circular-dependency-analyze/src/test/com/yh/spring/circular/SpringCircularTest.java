package com.yh.spring.circular;

import com.yh.spring.circular.service.ServiceA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCircularTest {

    public static void main(String[] args) {
        ApplicationContext xc = new ClassPathXmlApplicationContext("spring.xml");
        ServiceA serviceA = xc.getBean(ServiceA.class);
        System.out.println(serviceA.test());
    }

}
