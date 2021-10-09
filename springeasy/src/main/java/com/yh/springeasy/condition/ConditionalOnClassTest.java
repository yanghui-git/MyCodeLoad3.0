package com.yh.springeasy.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConditionalOnClassTest {

    @ConditionalOnClass(name="com.yh.springeasy.SpringEasyMain")
    @Bean
    public ConditionalOnClassDemo getConditionalOnClass() {
        System.out.println("验证某个class位于类路径上，才会实例化一个Bean 1");
        return ConditionalOnClassDemo.builder().build();
    }

    @ConditionalOnClass(name="com.yh.springeasy.SpringEasyMainHahahah")
    @Bean
    public ConditionalOnClassDemo getConditionalOnClass2() {
        System.out.println("验证某个class位于类路径上，才会实例化一个Bean 2");
        return ConditionalOnClassDemo.builder().build();
    }
}
