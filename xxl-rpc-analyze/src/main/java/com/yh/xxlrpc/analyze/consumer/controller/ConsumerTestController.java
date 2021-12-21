package com.yh.xxlrpc.analyze.consumer.controller;

import com.yh.xxlrpc.analyze.consumer.demo.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ConsumerTestController {

    @XxlRpcReference
    private DemoService demoService;

    @GetMapping
    public void test() {
        System.out.println(demoService.say());
    }
}
