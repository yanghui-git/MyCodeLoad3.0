package com.yh.springeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations= {"classpath:initbean/initbean.xml"})
public class SpringEasyMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyMain.class);
    }

}
