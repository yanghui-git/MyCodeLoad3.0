package com.mytest.starter.util;

import java.lang.annotation.*;

/**
 * 自定义注解,用于RedisUtil功能的开关
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableOnRedis {

}