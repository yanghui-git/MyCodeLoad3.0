package com.yh.mybatisplus.cipher;

import com.yh.mybatisplus.demouser.entity.DemoUser;
import org.springframework.stereotype.Service;

@Service
public class CipherServiceImpl implements CipherService {

    /**
     * 动态加密
     *
     * @param demoUser
     */
    @CipherAnnotation
    @Override
    public void encrypt(@CipherAnnotation DemoUser demoUser) {
        System.out.println(demoUser.toString());
    }

    /**
     * 动态解密
     *
     * @param str
     */
    @Override
    @CipherAnnotation(mode = "decrypt")
    public String decrypt(String str) {
        return str;
    }

    /**
     * 加解密
     *
     * @param demoUser
     * @return
     */
    @Override
    @CipherAnnotation(mode = "decrypt")
    public String cipher(@CipherAnnotation(mode = "encrypt") DemoUser demoUser) {
        return demoUser.toString();
    }


}
