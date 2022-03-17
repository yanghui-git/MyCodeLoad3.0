package com.yh.spring.circular.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ServiceC {

    //C 中引用A
    @Autowired
    private ServiceA serviceA;

    public String test() {
        return "ServiceC";
    }
}
