package com.yh.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/cipherYh.xml")
public class MybatisPlusMain {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusMain.class);
    }
}
