package com.oathc.authresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@SpringBootApplication
public class AuthResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthResourceServerApplication.class, args);
    }

    @GetMapping("resource_server")
    public String test() {
        return "资源服务器数据获取成功" + new Date();
    }
}
