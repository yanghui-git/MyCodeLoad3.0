package com.yh.test.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring.xml"})
public class NacosProviderMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderMain.class);
    }
}
