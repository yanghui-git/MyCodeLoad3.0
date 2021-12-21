package com.yh.xxlrpc.analyze.consumer.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XxlRpcBeanInitial {

    @Bean
    public XxlRpcSpringInvokerFactory getXxlRpcSpringInvokerFactory() {
        System.out.println("初始化构造bean XxlRpcSpringInvokerFactory");
        return new XxlRpcSpringInvokerFactory();
    }
}
