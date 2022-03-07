package com.yh.test.nacos.controller.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    public void sayHello() {
        System.out.println("6666");
    }

    public void sayHi() {
        System.out.println("Hi ");
    }
}
