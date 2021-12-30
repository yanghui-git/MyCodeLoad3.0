package com.yh.feign.consumer.controller;

import com.yh.feign.share.DemoService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 配置feign 远程服务调用bean
 *
 * spring 动态代理生成类
 */
@FeignClient(name = "feign-demo-service-test", url = "http://localhost:5002")
public interface DemoServiceImpl extends DemoService{

}

