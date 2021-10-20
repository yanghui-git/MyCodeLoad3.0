package com.yh.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring.xml"})
public class NacosMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosMain.class);
    }
}
