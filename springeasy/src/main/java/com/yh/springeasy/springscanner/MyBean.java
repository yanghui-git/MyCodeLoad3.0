package com.yh.springeasy.springscanner;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBean {

    int minConsumeThread() default 20;

    int maxConsumeThread() default 20;

    String consumerGroup() default "";

    /**
     * BROADCASTING - 广播
     * 其它 - 非广播
     * @return
     */
    String messageMode() default "";
}


