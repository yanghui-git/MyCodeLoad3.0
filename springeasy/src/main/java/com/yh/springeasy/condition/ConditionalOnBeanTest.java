package com.yh.springeasy.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConditionalOnBeanTest {

    @ConditionalOnBean(annotation = EnableRedis.class)
    @Bean
    public ConditionalOnExpressionDemo getConditionalOnExpressionDemo() {
        System.out.println("验证启动类加上了@EnableRedis,才会去装载Redis相关的Bean配置");
        return ConditionalOnExpressionDemo.builder().name("test").age(20).build();
    }

}
