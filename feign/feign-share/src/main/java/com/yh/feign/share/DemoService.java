package com.yh.feign.share;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 对外服务暴露的 feign 接口
 */
@RequestMapping("/demo")
public interface DemoService {

    @GetMapping("/one")
    String testOne(@RequestParam("name") String name);
}
