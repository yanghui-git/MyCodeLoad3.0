package com.yh.mybatisplus.cipher;

import com.yh.mybatisplus.demouser.entity.DemoUser;

public interface CipherService {

    void encrypt(DemoUser demoUser);

    String decrypt(String str);

    String cipher(DemoUser demoUser);
}
