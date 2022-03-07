package com.yh.test.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider-test/discovery")
public class DiscoverController {


    @GetMapping("/one")
    public Object testOne() {
        System.out.println(System.getProperty("server.port"));
        return "当前执行机器" + System.getProperty("server.port");
    }

}