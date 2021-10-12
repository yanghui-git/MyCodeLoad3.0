package com.yh.springeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ImportResource(locations= {"classpath:initbean/initbean.xml"})
public class SpringEasyMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyMain.class);
    }

    @RestController
    class TestDemo {

        @GetMapping
        public String test() {
            return " start success";
        }
    }
}
