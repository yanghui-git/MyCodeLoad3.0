package com.yh.xxlrpc.analyze.consumer.controller;

import java.lang.annotation.*;

/**
 * 注解标注   表示某service 进行远程调用
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlRpcReference {

}
