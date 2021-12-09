package com.yh.mybatisplus.demouser.service.impl;

import com.yh.mybatisplus.demouser.entity.DemoUser;
import com.yh.mybatisplus.demouser.mapper.DemoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 默认情况下此注解会对unchecked异常进行回滚，对checked异常不回滚
 */
@Service
public class DemoUserServiceImplThree {

    @Autowired
    private DemoUserMapper demoUserMapper;

   // @Transactional
    public void addDemoUser(DemoUser demoUser) {
        demoUserMapper.insert(demoUser);
        throw new RuntimeException("uncheck 异常");
    }

 //  @Transactional
    public void addDemoUserTwo(DemoUser demoUser)  {
        demoUserMapper.insert(demoUser);
        String a=null;
        a.equals("2");
    }

    /**
     * check exception 不回滚
     *
     * @param demoUser
     */
    @Transactional
    public void addDemoUserThree(DemoUser demoUser) throws IOException {
        demoUserMapper.insert(demoUser);
        throw new IOException();
    }


    /**
     * check exception 不回滚
     *
     * @param demoUser
     */
    @Transactional
    public void addDemoUserFour(DemoUser demoUser) throws TimeoutException {
        demoUserMapper.insert(demoUser);
        throw new TimeoutException();
    }

    /**
     * check exception 回滚方案
     *
     * @param demoUser
     */
    @Transactional(rollbackFor = TimeoutException.class)
    public void addDemoUserFive(DemoUser demoUser) throws TimeoutException {
        demoUserMapper.insert(demoUser);
        throw new TimeoutException();
    }
}
