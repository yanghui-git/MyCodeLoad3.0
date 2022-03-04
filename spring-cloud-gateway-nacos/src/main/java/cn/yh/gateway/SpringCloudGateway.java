package cn.yh.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 开启服务注册和发现
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGateway {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGateway.class, args);
    }
}
