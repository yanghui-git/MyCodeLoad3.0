package com.yh.springeasy;

import com.yh.springeasy.condition.EnableRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations= {"classpath:initbean/initbean.xml"})
@EnableRedis
public class SpringEasyMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyMain.class);
    }

}
