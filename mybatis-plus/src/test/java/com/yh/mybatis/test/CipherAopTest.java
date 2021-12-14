package com.yh.mybatis.test;

import com.yh.mybatisplus.MybatisPlusMain;
import com.yh.mybatisplus.cipher.CipherAop;
import com.yh.mybatisplus.cipher.CipherService;
import com.yh.mybatisplus.demouser.entity.DemoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;


// 设置启动类
@SpringBootTest(classes = MybatisPlusMain.class)
@RunWith(SpringRunner.class)
@ImportResource("classpath:/cipherYh.xml")
public class CipherAopTest {

    @Autowired
    private CipherService cipherService;

    @Autowired
    private CipherAop cipherAop;

    /**
     * 动态加密
     */
    @Test
    public void encrypt() {
        cipherService.encrypt(DemoUser.builder().age(1).build());
    }

    /**
     * 动态解密
     */
    @Test
    public void decrypt() {
        System.out.println(cipherService.decrypt("123"));
    }

    /**
     * 动态加解密
     */
    @Test
    public void cipher() {
        System.out.println(cipherService.cipher(DemoUser.builder().age(100).build()));
    }
}
