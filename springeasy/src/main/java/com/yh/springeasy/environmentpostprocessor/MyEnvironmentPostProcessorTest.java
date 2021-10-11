package com.yh.springeasy.environmentpostprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EnvironmentPostProcessor")
public class MyEnvironmentPostProcessorTest {

    @Autowired
    private Environment environment;

    @GetMapping("/one")
    public void testOne() {
        System.out.println(environment.getProperty("yh.EnvironmentPostProcessor.test.one"));
        System.out.println(environment.getProperty("yh.EnvironmentPostProcessor.test.two"));
        System.out.println(environment.getProperty("test.env.yh"));
        System.out.println(environment.getProperty("dubbo.consumer.check.yh"));
    }
}
