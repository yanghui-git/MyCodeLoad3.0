package com.yh.springeasy.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @ConditionalOnMissingBean，它是修饰bean的一个注解，主要实现的是，
 * 当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，
 * 即你的实例只有一个，当你注册多个相同的bean时，会出现异常，以此来告诉开发人员。
 *
 * https://www.cnblogs.com/lori/p/13490005.html
 *
 */
@Component
public class ConditionalOnMissingBeanTest {

    @ConditionalOnMissingBean
    @Bean
    public ConditionalOnMissingBeanDemo getConditionalOnMissingBeanDem() {
       System.out.println("ConditionalOnMissingBeanDemo one");
       return ConditionalOnMissingBeanDemo.builder().build();
    }

    @ConditionalOnMissingBean
    @Bean
    public ConditionalOnMissingBeanDemo getConditionalOnMissingBeanDemoTwo() {
        System.out.println("ConditionalOnMissingBeanDemo Two");
        return ConditionalOnMissingBeanDemo.builder().build();
    }
}
