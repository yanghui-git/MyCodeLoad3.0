package com.yh.springeasy.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * https://blog.csdn.net/lbh199466/article/details/88303897
 *
 */
@Component
public class ConditionalOnExpressionTest {

    @ConditionalOnExpression(value = "1>11")
    @Bean
    public ConditionalOnExpressionDemo getConditionalOnExpressionDemo() {
        System.out.println(" 验证 当表达式为true的时候，才会实例化一个Bean 1 ");
        return ConditionalOnExpressionDemo.builder().name("test").age(20).build();
    }

    @ConditionalOnExpression(value = "50>10")
    @Bean
    public ConditionalOnExpressionDemo getConditionalOnExpressionDemo2() {
        System.out.println(" 验证 当表达式为true的时候，才会实例化一个Bean 2");
        return ConditionalOnExpressionDemo.builder().name("test").age(20).build();
    }
}
