package com.yh.springeasy.importtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Import 标签demo 赏析
 *
 * https://zhuanlan.zhihu.com/p/191414673
 *
 */
@RestController
@RequestMapping("/import")
public class ImportTestController {

    @Autowired
    private ImportDemoOne importDemoOne;

    @Autowired
    private ImportDemoTwo importDemoTwo;

    @Autowired
    private ImportDemoThree importDemoThree;

    @GetMapping("/one")
    public void testOne() {
        importDemoOne.say();
    }

    @GetMapping("/two")
    public void testTwo() {
        importDemoTwo.say();
    }

    @GetMapping("/three")
    public void testThree() {
        importDemoThree.say();
    }

}
