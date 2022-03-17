package com.yh.spring.circular.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ServiceA {

    // A 中引用B
    @Autowired
    private ServiceB serviceB;

    public String test() {
        return "ServiceA";
    }
}
