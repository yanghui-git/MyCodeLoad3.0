package com.yh.springeasy;

import com.mytest.starter.util.EnableOnRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations= {"classpath:initbean/initbean.xml"})

//测试springboot-myredis-starter
@EnableOnRedis
public class SpringEasyMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyMain.class);
    }

}
