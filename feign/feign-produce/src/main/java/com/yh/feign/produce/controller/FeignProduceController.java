package com.yh.feign.produce.controller;

import com.yh.feign.share.DemoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignProduceController implements DemoService {

    /**
     *  curl http://localhost:5001/consumer/one
     *
     * @param name
     * @return
     */
    @Override
    public String testOne(@RequestParam("name") String name) {
        return "feign-produce your name is" + name;
    }
}
