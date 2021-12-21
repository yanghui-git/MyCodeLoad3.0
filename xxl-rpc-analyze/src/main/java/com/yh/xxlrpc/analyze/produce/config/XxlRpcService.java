package com.yh.xxlrpc.analyze.produce.config;

import java.lang.annotation.*;

/**
 * 注解 表示远程调用的方法 暴露服务
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlRpcService {

    /**
     * @return
     */
    String version() default "";

}
