package com.yh.test.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider-test")
public class DiscoverController {

    @GetMapping("/discovery/one")
    public Object testOne() throws InterruptedException {
        System.out.println(System.getProperty("server.port"));

        // Hystrix测试,模拟并发访问,接口不同执行时间

        Thread.sleep((int) (Math.random() * 10000));
        return "当前执行机器" + System.getProperty("server.port");
    }

    /**
     * HYstirx 熔断发生跳转url
     *
     * @return
     */
    @RequestMapping(value = "/fallback")
    public String fallback() {
        System.out.println("fallback****************Gateway");
        return "Hystrix 当前系统繁忙,请稍后再试";
    }
}