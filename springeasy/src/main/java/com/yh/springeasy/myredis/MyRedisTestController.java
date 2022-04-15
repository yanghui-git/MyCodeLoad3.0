package com.yh.springeasy.myredis;

import com.mytest.starter.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot-myredis-starter 启动验证
 */
@RestController
@RequestMapping("/myredis")
public class MyRedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/one")
    public void testOne() {
        redisUtil.set("1", "test1");
    }
}
