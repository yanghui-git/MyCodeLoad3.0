package com.yh.mybatisplus.demouser.service.impl;

import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import com.yh.mybatisplus.demouser.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 探究spring 事物机制
 * 描述单个方法或类上的事务属性。
 * 在类级别，此注释默认应用于声明类及其子类的所有方法
 */
@Transactional
@Service
public class DemoUserServiceImpl implements DemoUserService {

    @Autowired
    private DemoUserMapper demoUserMapper;

    @Override
    public void addDemoUser(DemoUser demoUser) {
            demoUserMapper.insert(demoUser);
            int i = 1 / 0;
    }

    /**
     * 探究事物失效1：  只能应用于public
     *
     * @param demoUser
     */
    public void addDemoUserTwo(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        int i = 1 / 0;
    }



}
