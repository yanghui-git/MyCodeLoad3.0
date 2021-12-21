package com.yh.xxlrpc.analyze.produce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class XxlRpcSpringProviderConfig {

    @Bean
    public XxlRpcSpringProviderFactory getXxlRpcSpringProviderFactory(){
        return new XxlRpcSpringProviderFactory();
    }
}
