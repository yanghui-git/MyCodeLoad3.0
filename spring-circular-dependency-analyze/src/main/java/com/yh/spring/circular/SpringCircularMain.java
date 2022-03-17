package com.yh.spring.circular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring 循环依赖分析专题讲解demo
 * https://juejin.cn/post/6844904122160775176#heading-2
 * https://juejin.cn/post/6942698857260122143#heading-25
 */
@SpringBootApplication
@ImportResource("spring.xml")
public class SpringCircularMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringCircularMain.class);
    }
}
