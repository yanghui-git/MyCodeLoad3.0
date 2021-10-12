package com.yh.springeasy.initbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 模拟热更新 测试
 */
@RestController
@RequestMapping("/dynamic")
public class InitBeanTestDynamicController {

    @Autowired
    private InitBeanTestDynamic initBeanTestDynamic;

    @GetMapping
    public void test() throws Exception {
        while (true) {
            Thread.sleep(2000l);
            System.out.println("模拟业务获取热更新值 age=  " + initBeanTestDynamic.age);
        }
    }

}
