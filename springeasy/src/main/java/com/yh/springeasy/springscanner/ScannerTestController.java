package com.yh.springeasy.springscanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springscaner")
public class ScannerTestController {

    @Autowired
    private TestScannerBeanOne testScannerBeanOne;

    @Autowired
    private TestScannerBeanTwo testScannerBeanTwo;

    @Autowired
    private TestScannerBeanThree testScannerBeanThree;

    /**
     * 获取自定义属性测试
     */
    @GetMapping("/one")
    public void testOne() {
        System.out.println(testScannerBeanOne);
        System.out.println(testScannerBeanOne.getClass().getAnnotation(MyBean.class));
    }

}
