package com.yh.nacos.controller;

import com.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider-test/discovery")
public class DiscoverController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/one")
    public void testOne() {
        demoService.sayHello();
        demoService.sayHi();
    }

}