package com.yh.spring.circular.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ServiceB {

    // B 中引用C
    @Autowired
    private ServiceC serviceC;

    public String test() {
        return "ServiceB";
    }
}
