package com.yh.feign.consumer.controller;

import com.yh.feign.share.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class FeignConsumerController {

    /**
     * feign 接口
     */
    @Autowired
    private DemoService demoService;

    /**
     * 进行远程调用
     */
    @GetMapping("/one")
    public void testOne() {
        String name = "haha-xixi";
        System.out.println(demoService.testOne(name));
    }
}
