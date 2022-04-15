package com.mytest.starter.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 使用此注解
 * 表示当Spring容器中,有EnableRedis注解存在时, 才会去开启此配置,
 */
@ConditionalOnBean(annotation = EnableOnRedis.class)
@Component
public class EnableRedis {

    // 装载实例化工具类
    @Bean
    public RedisUtil getRedisUtil() {
        return new RedisUtil();
    }
}
