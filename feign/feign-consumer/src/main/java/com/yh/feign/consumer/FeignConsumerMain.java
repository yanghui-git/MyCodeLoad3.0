package com.yh.feign.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * feign 服务消费者端
 *
 */
@SpringBootApplication
@EnableFeignClients
public class FeignConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerMain.class);
    }
}
