package com.yh.xxlrpc.analyze.consumer.demo;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    public String say() {
        System.out.println("666666");
        return "88888";
    }
}
