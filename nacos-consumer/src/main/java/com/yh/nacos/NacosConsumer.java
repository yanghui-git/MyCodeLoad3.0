package com.yh.nacos;

import com.demo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class NacosConsumer {

    public static void main(String[] args) throws IOException {
        System.out.println("开始启动nacos-consuer 服务");

        ClassPathXmlApplicationContext classXml = new ClassPathXmlApplicationContext("classpath:/spring-dubbo-two.xml");
        classXml.start();

        //模拟调用
        DemoService demoService = classXml.getBean(DemoService.class);
        demoService.sayHello();
        //demoService.sayHi();

        System.in.read();
    }
}
