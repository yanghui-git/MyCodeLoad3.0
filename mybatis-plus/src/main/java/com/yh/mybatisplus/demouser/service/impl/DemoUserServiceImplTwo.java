package com.yh.mybatisplus.demouser.service.impl;

import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 探究事物失效1：  只能应用于public
 */
@Transactional
@Service
public class DemoUserServiceImplTwo {

    @Autowired
    private DemoUserMapper demoUserMapper;

    public void addDemoUser(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        int i = 1 / 0;
    }

    private void addDemoUserTwo(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        int i = 1 / 0;
    }

    protected void addDemoUserThree(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        int i = 1 / 0;
    }

}
