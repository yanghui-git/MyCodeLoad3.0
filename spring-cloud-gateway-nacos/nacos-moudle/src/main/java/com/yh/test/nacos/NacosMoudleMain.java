package com.yh.test.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosMoudleMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosMoudleMain.class);
    }
}
