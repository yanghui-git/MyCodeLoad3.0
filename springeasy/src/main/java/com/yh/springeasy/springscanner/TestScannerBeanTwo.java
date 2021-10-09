package com.yh.springeasy.springscanner;

import lombok.Data;
import org.springframework.stereotype.Component;

@MyBean(maxConsumeThread = 1000,minConsumeThread = 50,messageMode = "yhtest")
/**
 * 需要去掉spring 注解 否则 该拦截不会自动生效
 * MutablePropertyValues propertyValues = item.getPropertyValues();
 */
//@Component
@Data
class TestScannerBeanTwo {

    String name;

    String appName;
}
